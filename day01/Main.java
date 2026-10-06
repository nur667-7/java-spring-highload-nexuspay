package day01;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== ДЕНЬ 1: RICH DOMAIN MODEL & INVARIANTS ===");

        // 1. Создаем товары
        OrderItem phone = new OrderItem(UUID.randomUUID(), 1, new BigDecimal("999.99"));
        OrderItem caseCover = new OrderItem(UUID.randomUUID(), 2, new BigDecimal("19.99"));

        List<OrderItem> cart = new ArrayList<>();
        cart.add(phone);
        cart.add(caseCover);

        // 2. Создаем заказ
        Order order = new Order(UUID.randomUUID(), cart);
        System.out.println("\n[1] Заказ успешно создан! ID: " + order.getId());
        System.out.println("    Итоговая сумма: $" + order.calculateTotal());
        printStatus("    Статус заказа", order.getStatus());

        // 3. Проверяем защиту от модификации извне (Defensive Copying)
        System.out.println("\n[2] Проверка инкапсуляции списка позиций:");
        cart.clear(); // Очистили внешнюю корзину
        System.out.println("    Размер внешней корзины cart.size(): " + cart.size());
        System.out.println("    Размер товаров внутри order.getItems().size(): " + order.getItems().size());
        if (order.getItems().size() == 2) {
            System.out.println("    -> УСПЕХ: Внешний список не повлиял на сущность заказа!");
        }

        // 4. Оплачиваем заказ (штатный сценарий)
        System.out.println("\n[3] Оплата заказа через order.pay():");
        order.pay("tx-stripe-984712");
        printStatus("    Новый статус", order.getStatus());

        // 5. Попытка взлома инварианта: отменить уже оплаченный заказ!
        System.out.println("\n[4] Попытка нарушить инвариант: вызвать order.cancel() на оплаченном заказе...");
        try {
            order.cancel("Клиент передумал");
        } catch (IllegalStateException e) {
            System.out.println("    -> ЗАЩИТА СРАБОТАЛА! Исключение перехвачено:");
            System.out.println("       Причина: " + e.getMessage());
        }

        // 6. Попытка создать невалидный товар
        System.out.println("\n[5] Попытка создать товар с отрицательной ценой...");
        try {
            new OrderItem(UUID.randomUUID(), 1, new BigDecimal("-50.00"));
        } catch (IllegalArgumentException e) {
            System.out.println("    -> ЗАЩИТА СРАБОТАЛА! Компактный конструктор отклонил данные:");
            System.out.println("       Причина: " + e.getMessage());
        }

        System.out.println("\n=== ВСЕ ИНВАРИАНТЫ УСПЕШНО ЗАЩИЩЕНЫ ===");
    }

    /**
     * Pattern Matching для switch (Java 21).
     * Компилятор проверяет полноту (Exhaustiveness).
     * Блок default НЕ требуется, так как OrderStatus — sealed!
     */
    private static void printStatus(String prefix, OrderStatus status) {
        String description = switch (status) {
            case OrderStatus.Created c -> "CREATED (время создания: " + c.createdAt() + ")";
            case OrderStatus.Paid p    -> "PAID (id транзакции: " + p.transactionId() + ", оплачен в: " + p.paidAt() + ")";
            case OrderStatus.Cancelled c -> "CANCELLED (причина: '" + c.reason() + "', отменен в: " + c.cancelledAt() + ")";
        };
        System.out.println(prefix + ": " + description);
    }
}
