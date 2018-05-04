package com.project.course.entity;

import java.util.Objects;

/**
 * Created by kleba on 30.04.2018.
 */
public class UserRole {
    private long urId;
    private long userId;
    private long roleId;

    public UserRole(){};

    public UserRole(long userId, long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public long getUrId() {
        return urId;
    }

    public void setUrId(long urId) {
        this.urId = urId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole that = (UserRole) o;
        return urId == that.urId &&
                userId == that.userId &&
                roleId == that.roleId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(urId, userId, roleId);
    }
}
