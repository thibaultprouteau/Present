package com.example.attendance;

public class Lecture {
    private String startTime;
    private String endTime;
    private String lecturer;
    private String location;

    public Lecture(String startTime, String endTime, String lecturer, String location) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.lecturer = lecturer;
        this.location = location;
    }
}
