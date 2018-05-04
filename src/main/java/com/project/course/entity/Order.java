package com.project.course.entity;

import java.sql.Date;
import java.util.Objects;

/**
 * Created by kleba on 30.04.2018.
 */
public class Order {
    private long orderId;
    private Date date;
    private String status;
    private long userId;

    public Order(){};

    public Order(Date date, String status, long userId) {
        this.date = date;
        this.status = status;
        this.userId = userId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId &&
                userId == order.userId &&
                Objects.equals(date, order.date) &&
                Objects.equals(status, order.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, date, status, userId);
    }
}
