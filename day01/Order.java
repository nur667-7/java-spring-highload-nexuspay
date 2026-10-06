package day01;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

/**
 * Rich Domain Entity (Сущность с поведением).
 * 1. Инкапсулирует состояние.
 * 2. Публичных сеттеров нет.
 * 3. Содержит только бизнес-методы с проверкой инвариантов.
 */
public class Order {
    private final UUID id;
    private final List<OrderItem> items;
    private OrderStatus status;

    public Order(UUID id, List<OrderItem> items) {
        if (id == null) {
            throw new IllegalArgumentException("Order ID cannot be null");
        }
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one item");
        }
        this.id = id;
        // Защитная копия (Defensive Copy): внешний код больше не может изменить список
        this.items = List.copyOf(items);
        this.status = new OrderStatus.Created(Instant.now());
    }

    /**
     * Бизнес-метод: Оплата заказа.
     * Защищает инвариант: оплатить можно только заказ в статусе Created.
     */
    public void pay(String transactionId) {
        if (transactionId == null || transactionId.isBlank()) {
            throw new IllegalArgumentException("TransactionId is required");
        }
        if (!(this.status instanceof OrderStatus.Created)) {
            throw new IllegalStateException("Only CREATED orders can be paid. Current status: " 
                    + this.status.getClass().getSimpleName());
        }
        this.status = new OrderStatus.Paid(Instant.now(), transactionId);
    }

    /**
     * Бизнес-метод: Отмена заказа.
     * Защищает инвариант: оплаченный заказ отменить напрямую нельзя!
     */
    public void cancel(String reason) {
        if (reason == null || reason.isBlank()) {
            throw new IllegalArgumentException("Cancellation reason is required");
        }
        if (this.status instanceof OrderStatus.Paid) {
            throw new IllegalStateException("Cannot cancel an already PAID order. Issue refund first!");
        }
        if (this.status instanceof OrderStatus.Cancelled) {
            throw new IllegalStateException("Order is already cancelled!");
        }
        this.status = new OrderStatus.Cancelled(Instant.now(), reason);
    }

    /**
     * Бизнес-логика: Расчет итоговой стоимости.
     */
    public BigDecimal calculateTotal() {
        return items.stream()
                .map(OrderItem::calculateSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Только безопасные геттеры
    public UUID getId() {
        return id;
    }

    public List<OrderItem> getItems() {
        return items; // items уже unmodifiable благодаря List.copyOf
    }

    public OrderStatus getStatus() {
        return status;
    }
}
