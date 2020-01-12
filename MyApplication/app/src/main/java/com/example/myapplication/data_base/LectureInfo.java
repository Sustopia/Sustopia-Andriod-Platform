package com.example.myapplication.data_base;

public class LectureInfo implements Extensable{
    private long uniquenessId;

    private String department;
    private String name;
    private String teacher;
    private String room;
    private String time;
    private boolean isExtense;

    public LectureInfo(String department, String name, String teacher, String room, String time, long uniquenessId) {
        this.uniquenessId = uniquenessId;
        this.department = department;
        this.name = name;
        this.teacher = teacher;
        this.room = room;
        this.time = time;
    }

    public boolean isExtense() {
        return isExtense;
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

    public String getRoom() {
        return room;
    }

    public String getTime() {
        return time;
    }

    public void setExtense(boolean extense) {
        isExtense = extense;
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

    public void setRoom(String room) {
        this.room = room;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void changeExtense(){
        this.isExtense = !this.isExtense;
    }
}
