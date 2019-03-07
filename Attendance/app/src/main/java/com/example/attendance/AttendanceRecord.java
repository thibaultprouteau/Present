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
}
