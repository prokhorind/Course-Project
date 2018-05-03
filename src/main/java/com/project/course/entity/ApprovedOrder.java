package com.project.course.entity;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Created by kleba on 30.04.2018.
 */
public class ApprovedOrder {
    private long orderId;
    private BigDecimal price;

    private ApprovedOrder(){};

    public ApprovedOrder(long orderId, BigDecimal price) {
        this.orderId = orderId;
        this.price = price;
    }

    public ApprovedOrder(BigDecimal price) {
        this.price = price;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApprovedOrder that = (ApprovedOrder) o;
        return orderId == that.orderId &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, price);
    }
}
