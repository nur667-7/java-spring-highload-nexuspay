package day01;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Value Object позиции заказа.
 * Java record гарантирует иммутабельность (все поля final, нет сеттеров).
 */
public record OrderItem(UUID productId, int quantity, BigDecimal pricePerUnit) {

    /**
     * Компактный конструктор (Compact Constructor).
     * Проверяет инварианты ДО того, как объект появится в памяти.
     */
    public OrderItem {
        if (productId == null) {
            throw new IllegalArgumentException("ProductId cannot be null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0, got: " + quantity);
        }
        if (pricePerUnit == null || pricePerUnit.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0, got: " + pricePerUnit);
        }
    }

    public BigDecimal calculateSubtotal() {
        return pricePerUnit.multiply(BigDecimal.valueOf(quantity));
    }
}
