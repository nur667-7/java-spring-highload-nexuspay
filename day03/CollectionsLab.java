package day03;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CollectionsLab {

    // 1. Упрощенная кастомная HashMap (разрешение коллизий методом цепочек)
    static class SimpleHashMap<K, V> {
        private static class Node<K, V> {
            final int hash;
            final K key;
            V value;
            Node<K, V> next;

            Node(int hash, K key, V value, Node<K, V> next) {
                this.hash = hash;
                this.key = key;
                this.value = value;
                this.next = next;
            }
        }

        @SuppressWarnings("unchecked")
        private final Node<K, V>[] buckets = new Node[16]; // фиксированно 16 корзин

        private int index(int hash) {
            return (buckets.length - 1) & hash;
        }

        public void put(K key, V value) {
            int hash = Objects.hashCode(key);
            int idx = index(hash);

            Node<K, V> current = buckets[idx];
            if (current == null) {
                buckets[idx] = new Node<>(hash, key, value, null);
                return;
            }

            // Идем по цепочке в бакете (разрешение коллизии)
            while (true) {
                if (current.hash == hash && Objects.equals(current.key, key)) {
                    current.value = value; // Ключ совпал -> перезаписываем значение
                    return;
                }
                if (current.next == null) {
                    current.next = new Node<>(hash, key, value, null); // Прицепляем в конец списка
                    return;
                }
                current = current.next;
            }
        }

        public V get(K key) {
            int hash = Objects.hashCode(key);
            int idx = index(hash);

            Node<K, V> current = buckets[idx];
            while (current != null) {
                if (current.hash == hash && Objects.equals(current.key, key)) {
                    return current.value;
                }
                current = current.next;
            }
            return null;
        }
    }

    // 2. Антипаттерн №1: Переопределили equals(), но ЗАБЫЛИ hashCode()
    static class BrokenKey {
        private final String id;

        BrokenKey(String id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof BrokenKey that)) return false;
            return Objects.equals(id, that.id);
        }
        // hashCode() НЕ переопределен! Берется дефолтный из Object (зависит от адреса в Heap)
    }

    // 3. Антипаттерн №2: Мутабельный (изменяемый) ключ в HashMap
    static class MutableKey {
        private String name; // НЕ final!

        MutableKey(String name) {
            this.name = name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof MutableKey that)) return false;
            return Objects.equals(name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(name);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== ДЕНЬ 3: HASHMAP ПОД КАПОТОМ И КОНТРАКТ EQUALS/HASHCODE ===");

        // Тест 1: Своя SimpleHashMap с коллизией
        System.out.println("\n--- [1] Тест собственной SimpleHashMap ---");
        SimpleHashMap<String, Integer> customMap = new SimpleHashMap<>();
        customMap.put("Aa", 100);
        customMap.put("BB", 200); // У строк "Aa" и "BB" в Java ОДИНАКОВЫЙ hashCode = 2112 (коллизия!)
        System.out.println("hashCode('Aa') = " + "Aa".hashCode() + ", hashCode('BB') = " + "BB".hashCode());
        System.out.println("customMap.get('Aa') = " + customMap.get("Aa"));
        System.out.println("customMap.get('BB') = " + customMap.get("BB"));

        // Тест 2: Нарушение контракта (нет hashCode)
        System.out.println("\n--- [2] Баг: есть equals(), но нет hashCode() ---");
        Map<BrokenKey, String> brokenMap = new HashMap<>();
        BrokenKey k1 = new BrokenKey("order-777");
        brokenMap.put(k1, "Заказ на MacBook");

        BrokenKey k2 = new BrokenKey("order-777");
        System.out.println("k1.equals(k2) ? -> " + k1.equals(k2));
        System.out.println("k1.hashCode() = " + k1.hashCode() + " | k2.hashCode() = " + k2.hashCode());
        System.out.println("brokenMap.get(k2) -> " + brokenMap.get(k2) + " (ОБЪЕКТ ПОТЕРЯН!)");

        // Тест 3: Мутабельный ключ
        System.out.println("\n--- [3] Баг: изменили поле ключа после put() ---");
        Map<MutableKey, String> mutableMap = new HashMap<>();
        MutableKey userKey = new MutableKey("Alice");
        mutableMap.put(userKey, "Admin Rights"); // Положили в корзину для хэша "Alice"

        userKey.setName("Bob"); // Мутировали ключ! Теперь его hashCode считается от "Bob"
        System.out.println("Ищем по новому имени 'Bob': " + mutableMap.get(userKey));
        System.out.println("Ищем по старому имени 'Alice': " + mutableMap.get(new MutableKey("Alice")));
        System.out.println("Размер мапы: " + mutableMap.size() + ", но достать элемент невозможно!");
    }
}
