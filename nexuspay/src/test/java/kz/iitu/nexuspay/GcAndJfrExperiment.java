package kz.iitu.nexuspay;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GcAndJfrExperiment {
    private static final List<String> OLD_GEN_CACHE = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        System.out.println("PID: " + ProcessHandle.current().pid());
        long durationMs = args.length > 0 ? Long.parseLong(args[0]) : 20_000L;
        long end = System.currentTimeMillis() + durationMs;
        int iteration = 0;

        while (System.currentTimeMillis() < end) {
            List<byte[]> shortLived = new ArrayList<>(1000);
            for (int i = 0; i < 1000; i++) {
                shortLived.add(new byte[1024]);
            }
            if (iteration++ % 20 == 0 && OLD_GEN_CACHE.size() < 50_000) {
                OLD_GEN_CACHE.add(UUID.randomUUID().toString());
            }
            Thread.sleep(10);
        }
        System.out.println("Experiment finished. Cache size: " + OLD_GEN_CACHE.size());
    }
}
