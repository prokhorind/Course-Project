package com.project.course.entity;

import java.util.Objects;

/**
 * Created by kleba on 30.04.2018.
 */
public class Detail {
    private long detailId;
    private String name;
    private String reason;
    private long orderId;

    public Detail(){};

    public Detail(long detailId, String name, String reason, long orderId) {
        this.detailId = detailId;
        this.name = name;
        this.reason = reason;
        this.orderId = orderId;
    }

    public Detail(String name, String reason, long orderId) {
        this.name = name;
        this.reason = reason;
        this.orderId = orderId;
    }

    public long getDetailId() {
        return detailId;
    }

    public void setDetailId(long detailId) {
        this.detailId = detailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Detail detail = (Detail) o;
        return detailId == detail.detailId &&
                orderId == detail.orderId &&
                Objects.equals(name, detail.name) &&
                Objects.equals(reason, detail.reason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(detailId, name, reason, orderId);
    }
}
