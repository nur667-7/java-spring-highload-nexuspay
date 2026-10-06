package day04;

import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrencyLab {

    // 1. Обычный int (гонка данных, потеря инкрементов)
    private static int unsafeCount = 0;

    // 2. volatile int (гарантирует видимость между ядрами CPU, но НЕ атомарность!)
    private static volatile int volatileCount = 0;

    // 3. synchronized счетчик (пессимистичная блокировка монитором)
    private static int syncCount = 0;
    private static final Object LOCK = new Object();

    // 4. AtomicInteger (оптимистичный Lock-Free на аппаратной инструкции CAS)
    private static final AtomicInteger atomicCount = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== ДЕНЬ 4: МНОГОПОТОЧНОСТЬ, RACE CONDITION, VOLATILE И CAS ===");

        int threadsCount = 100;
        int incrementsPerThread = 10_000;
        int expected = threadsCount * incrementsPerThread; // Ожидаем 1 000 000

        // Запускаем 100 потоков
        Thread[] threads = new Thread[threadsCount];

        long start = System.nanoTime();
        for (int i = 0; i < threadsCount; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < incrementsPerThread; j++) {
                    unsafeCount++;
                    volatileCount++;
                    synchronized (LOCK) {
                        syncCount++;
                    }
                    atomicCount.incrementAndGet();
                }
            });
            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }
        long totalMs = (System.nanoTime() - start) / 1_000_000;

        System.out.println("\n--- [1] Результаты 100 потоков по 10 000 инкрементов (Ожидаем: " + expected + ") ---");
        System.out.println("  unsafeCount   (обычный int)   = " + unsafeCount + "  <-- ПОТЕРЯНО: " + (expected - unsafeCount));
        System.out.println("  volatileCount (volatile int)  = " + volatileCount + "  <-- ПОТЕРЯНО: " + (expected - volatileCount));
        System.out.println("  syncCount     (synchronized)  = " + syncCount + "  <-- ТОЧНО!");
        System.out.println("  atomicCount   (Atomic / CAS)  = " + atomicCount.get() + "  <-- ТОЧНО!");

        // Замер чистой скорости: synchronized vs AtomicInteger
        benchmarkSyncVsAtomic(threadsCount, incrementsPerThread);
    }

    private static void benchmarkSyncVsAtomic(int threadsCount, int incrementsPerThread) throws InterruptedException {
        System.out.println("\n--- [2] Чистый бенчмарк скорости: synchronized vs AtomicInteger ---");

        // 1. Замер synchronized
        syncCount = 0;
        Thread[] syncThreads = new Thread[threadsCount];
        long startSync = System.nanoTime();
        for (int i = 0; i < threadsCount; i++) {
            syncThreads[i] = new Thread(() -> {
                for (int j = 0; j < incrementsPerThread; j++) {
                    synchronized (LOCK) {
                        syncCount++;
                    }
                }
            });
            syncThreads[i].start();
        }
        for (Thread t : syncThreads) t.join();
        long syncMs = (System.nanoTime() - startSync) / 1_000_000;

        // 2. Замер AtomicInteger (CAS)
        atomicCount.set(0);
        Thread[] atomicThreads = new Thread[threadsCount];
        long startAtomic = System.nanoTime();
        for (int i = 0; i < threadsCount; i++) {
            atomicThreads[i] = new Thread(() -> {
                for (int j = 0; j < incrementsPerThread; j++) {
                    atomicCount.incrementAndGet();
                }
            });
            atomicThreads[i].start();
        }
        for (Thread t : atomicThreads) t.join();
        long atomicMs = (System.nanoTime() - startAtomic) / 1_000_000;

        System.out.println("  synchronized время : " + syncMs + " ms (результат: " + syncCount + ")");
        System.out.println("  AtomicInteger время: " + atomicMs + " ms (результат: " + atomicCount.get() + ")");
    }
}
