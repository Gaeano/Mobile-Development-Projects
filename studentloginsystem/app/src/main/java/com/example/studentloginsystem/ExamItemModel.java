package com.example.studentloginsystem;

public class ExamItemModel {

    private String name;
    private String date;
    private String message;
    private int imgclock;
    private int imggrade;

    public ExamItemModel(String name, String date, String message, int imgclock, int imggrade) {
        this.name  = name;
        this.date = date;
        this.message = message;
        this.imgclock = imgclock;
        this.imggrade = imggrade;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getImgclock() {
        return imgclock;
    }

    public void setImgclock(int imgclock) {
        this.imgclock = imgclock;
    }

    public int getImggrade() {
        return imggrade;
    }

    public void setImggrade(int imggrade) {
        this.imggrade = imggrade;
    }
}
