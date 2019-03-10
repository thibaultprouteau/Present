package com.example.attendance;

public class Lecture {
    private Integer idLecture;
    private String startTime;
    private String endTime;
    private String lecturer;
    private String location;
    private Integer idGroup;
    private Integer idCours;


    public Lecture(Integer idLecture, String startTime, String endTime, String lecturer, String location, Integer idGroup, Integer idCours) {
        this.idLecture = idLecture;
        this.startTime = startTime;
        this.endTime = endTime;
        this.lecturer = lecturer;
        this.location = location;
        this.idGroup = idGroup;
        this.idCours = idCours;
    }

    public Integer getIdLecture() {
        return idLecture;
    }

    public void setIdLecture(Integer idLecture) {
        this.idLecture = idLecture;
    }

    public Integer getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(Integer idGroup) {
        this.idGroup = idGroup;
    }

    public Integer getIdCours() {
        return idCours;
    }

    public void setIdCours(Integer idCours) {
        this.idCours = idCours;
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
