package com.example.myapplication.data_base;

import java.net.URI;

public class RefInfo implements Extensable {
    private long uniquenessId;

    private String department;
    private String name;
    private String teacher;
    private String minimum;
    private String average;
    private String successRate;
    private boolean isExtense;
    public RefInfo(String department, String name, String teacher, String minimum, String average, String successRate, long uniquenessId) {
        this.uniquenessId = uniquenessId;
        this.department = department;
        this.name = name;
        this.teacher = teacher;
        this.minimum = minimum;
        this.average = average;
        this.successRate = successRate;
    }

    public long getUniquenessId() {
        return uniquenessId;
    }

    public String getDepartment() {
        return department;
    }

    public String getName() {
        return name;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getMinimum() {
        return minimum;
    }

    public String getAverage() {
        return average;
    }

    public String getSuccessRate() {
        return successRate;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setMinimum(String minimum) {
        this.minimum = minimum;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    public void setSuccessRate(String successRate) {
        this.successRate = successRate;
    }

    @Override
    public boolean isExtense() {
        return isExtense;
    }

    @Override
    public void changeExtense() {
        isExtense = !isExtense;
    }
}
