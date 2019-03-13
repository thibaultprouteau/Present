package com.example.attendance;

public class Course {
    private Integer courseId;
    private String courseName;
    private String courseDesc;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDesc() {
        return courseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }

    public Course(Integer courseId, String courseName, String courseDesc) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseDesc = courseDesc;
    }


}
