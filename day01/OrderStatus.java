package day01;

import java.time.Instant;

/**
 * Изолированный интерфейс состояний.
 * Только перечисленные типы имеют право быть статусом заказа.
 */
public sealed interface OrderStatus permits 
        OrderStatus.Created, 
        OrderStatus.Paid, 
        OrderStatus.Cancelled {

    record Created(Instant createdAt) implements OrderStatus {}
    
    record Paid(Instant paidAt, String transactionId) implements OrderStatus {}
    
    record Cancelled(Instant cancelledAt, String reason) implements OrderStatus {}
}
