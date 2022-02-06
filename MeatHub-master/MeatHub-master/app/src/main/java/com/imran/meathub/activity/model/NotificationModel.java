package com.imran.meathub.activity.model;

public class NotificationModel {
    private String notification,date,time;

    public NotificationModel(String notification, String date, String time) {
        this.notification = notification;
        this.date = date;
        this.time = time;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
