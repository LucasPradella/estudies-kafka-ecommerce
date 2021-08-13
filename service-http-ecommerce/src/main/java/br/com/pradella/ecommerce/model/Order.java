package br.com.pradella.ecommerce.model;

import java.math.BigDecimal;

public class Order {

    private final String email, orderId;
    private final BigDecimal amount;

    public Order(String email, String orderId, BigDecimal amount) {
        this.email = email;
        this.orderId = orderId;
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Order{" +
                "email='" + email + '\'' +
                ", orderId='" + orderId + '\'' +
                ", amount=" + amount +
                '}';
    }

    public boolean isFraud() {
        return this.amount.compareTo(new BigDecimal("4500")) >= 0;
    }
}
