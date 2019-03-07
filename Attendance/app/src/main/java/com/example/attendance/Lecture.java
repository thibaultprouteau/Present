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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
