package com.baoge.entity;

import com.google.common.base.MoreObjects;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2020/3/2
 */
public class Employee {

    private String name;

    private String departMentId;

    private String employeeId;

    public Employee(String name, String departMentId, String employeeId) {
        this.name = name;
        this.departMentId = departMentId;
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public String getDepartMentId() {
        return departMentId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("Name", getName())
                .add("DepartmentId", getDepartMentId())
                .add("EmployeeId", getEmployeeId()).toString();
    }
}
