package com.example.myapplication.data_base;

public class ClassInfo implements Extensable{
    private long uniquenessId;

    private String courseId;
    private String name;
    private String teacher;
    private String room;
    private String time;
    private int credit;

    public void setUniquenessId(int uniquenessId) {
        this.uniquenessId = uniquenessId;
    }

    public long getUniquenessId() {
        return uniquenessId;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getCredit() {
        return credit;
    }

    private boolean isExtense;

    public void setExtense(boolean extense) {
        isExtense = extense;
    }

    public boolean isExtense() {
        return isExtense;
    }

    public ClassInfo(String courseId, String name, String teacher, String room, String time,int credit,int uniquenessId) {
        this.courseId = courseId;
        this.name = name;
        this.teacher = teacher;
        this.room = room;
        this.time = time;
        this.isExtense = false;
        this.credit = credit;
        this.uniquenessId = uniquenessId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
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

    public String getCourseId() {
        return courseId;
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

    public void changeExtense(){
        this.isExtense = !this.isExtense;
    }
}
