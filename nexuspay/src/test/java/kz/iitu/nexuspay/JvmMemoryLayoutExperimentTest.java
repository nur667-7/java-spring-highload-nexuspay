package kz.iitu.nexuspay;

import org.junit.jupiter.api.Test;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JvmMemoryLayoutExperimentTest {

    record AccountSnapshot(UUID id, BigDecimal balance, long version, boolean active) {}

    @Test
    void inspectObjectHeaderAndRecordLayout() {
        System.out.println("=== 1. JVM COMPRESSED OOPS DETAILS ===");
        System.out.println(VM.current().details());

        Object lock = new Object();
        ClassLayout objectLayout = ClassLayout.parseInstance(lock);
        System.out.println("=== 2. FRESH OBJECT (BEFORE HASHCODE) ===");
        System.out.println(objectLayout.toPrintable());
        assertEquals(16L, objectLayout.instanceSize(), "Empty Object with Compressed OOPs must be padded to 16 bytes");

        int hash = System.identityHashCode(lock);
        System.out.println("=== 3. AFTER IDENTITY HASHCODE (0x" + Integer.toHexString(hash) + ") ===");
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());

        synchronized (lock) {
            System.out.println("=== 4. INSIDE SYNCHRONIZED BLOCK (LOCKED MARK WORD) ===");
            System.out.println(ClassLayout.parseInstance(lock).toPrintable());
        }

        System.out.println("=== 5. JAVA 21 RECORD MEMORY LAYOUT ===");
        System.out.println(ClassLayout.parseClass(AccountSnapshot.class).toPrintable());
    }
}
