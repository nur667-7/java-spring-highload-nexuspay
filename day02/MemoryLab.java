package day02;

import day01.Order;
import day01.OrderItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MemoryLab {

    public static void main(String[] args) {
        System.out.println("=== ДЕНЬ 2: ПАМЯТЬ JVM (STACK, HEAP, PASS-BY-VALUE) ===");
        printMemoryStats("Старт JVM");

        // 1. Демонстрация Pass-by-Value
        System.out.println("\n--- [1] Тест Pass-by-Value ---");
        Order myOrder = new Order(
                UUID.randomUUID(),
                List.of(new OrderItem(UUID.randomUUID(), 1, new BigDecimal("100.00")))
        );

        tryToOverwriteReference(myOrder);
        System.out.println("После tryToOverwriteReference(order = null):");
        System.out.println("  myOrder жив? -> " + (myOrder != null) + " (Статус: " + myOrder.getStatus().getClass().getSimpleName() + ")");

        mutateObjectInHeap(myOrder);
        System.out.println("После mutateObjectInHeap(order.pay):");
        System.out.println("  Статус myOrder в Heap -> " + myOrder.getStatus().getClass().getSimpleName());

        // 2. Демонстрация StackOverflowError
        System.out.println("\n--- [2] Взрыв стека (StackOverflowError) ---");
        try {
            infiniteRecursion(1);
        } catch (StackOverflowError e) {
            System.out.println("  -> Пойман StackOverflowError! Глубина стека на момент падения: " + depth + " фреймов");
        }

        // 3. Демонстрация OutOfMemoryError: Java heap space
        System.out.println("\n--- [3] Взрыв кучи (OutOfMemoryError: Java heap space) ---");
        List<byte[]> memoryLeakList = new ArrayList<>();
        int megabytesAllocated = 0;
        try {
            while (true) {
                // Аллоцируем по 5 МБ в куче и держим ссылку в списке (GC не может удалить!)
                memoryLeakList.add(new byte[5 * 1024 * 1024]);
                megabytesAllocated += 5;
                System.out.println("  Аллоцировано в Heap: " + megabytesAllocated + " MB");
            }
        } catch (OutOfMemoryError e) {
            // Освобождаем ссылку, чтобы напечатать статистику
            memoryLeakList.clear();
            System.out.println("  -> Пойман OutOfMemoryError при попытке выделить " + (megabytesAllocated + 5) + " MB! Причина: " + e.getMessage());
        }

        printMemoryStats("После очистки списка");
    }

    /**
     * Перезаписывает локальную копию ссылки в своем Stack Frame.
     * Внешняя переменная myOrder в main() НЕ пострадает.
     */
    private static void tryToOverwriteReference(Order orderRefCopy) {
        orderRefCopy = null;
    }

    /**
     * Идет по скопированному адресу в общую кучу (Heap) и меняет сам объект.
     */
    private static void mutateObjectInHeap(Order orderRefCopy) {
        orderRefCopy.pay("tx-heap-777");
    }

    private static int depth = 0;
    private static void infiniteRecursion(int step) {
        depth = step;
        infiniteRecursion(step + 1);
    }

    private static void printMemoryStats(String label) {
        Runtime rt = Runtime.getRuntime();
        long maxMb = rt.maxMemory() / (1024 * 1024);
        long totalMb = rt.totalMemory() / (1024 * 1024);
        long usedMb = (rt.totalMemory() - rt.freeMemory()) / (1024 * 1024);
        long pid = ProcessHandle.current().pid();
        System.out.printf("[%s] PID: %d | Max Heap (-Xmx): %d MB | Allocated: %d MB | Used: %d MB%n",
                label, pid, maxMb, totalMb, usedMb);
    }
}
