package com.example.attendance;

public class AttendanceRecord {
    private Integer idLecture;
    private Integer idPerson;
    private String status;

    public AttendanceRecord(Integer idLecture, Integer idPerson, String status) {
        this.idLecture = idLecture;
        this.idPerson = idPerson;
        this.status = status;
    }

    public Integer getIdLecture() {
        return idLecture;
    }

    public void setIdLecture(Integer idLecture) {
        this.idLecture = idLecture;
    }

    public Integer getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Integer idPerson) {
        this.idPerson = idPerson;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
