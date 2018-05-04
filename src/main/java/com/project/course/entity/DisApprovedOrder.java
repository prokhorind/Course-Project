package com.project.course.entity;

import java.util.Objects;

/**
 * Created by kleba on 30.04.2018.
 */
public class DisApprovedOrder {
    private long orderId;
    private String reason;

    public DisApprovedOrder(){};

    public DisApprovedOrder(long orderId, String reason) {
        this.orderId = orderId;
        this.reason = reason;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DisApprovedOrder that = (DisApprovedOrder) o;
        return orderId == that.orderId &&
                Objects.equals(reason, that.reason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, reason);
    }
}
