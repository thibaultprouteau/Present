package com.example.attendance;

public class Person {
    private Integer idPerson;
    private String firstName;
    private String lastName;
    private Integer idGroup;

    public Person(Integer idPerson, String firstName, String lastName, Integer idGroup) {
        this.idPerson = idPerson;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idGroup = idGroup;
    }

    public Integer getIdPerson() {
        return idPerson;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getIdGroup() {
        return idGroup;
    }

    public void setIdPerson(Integer idPerson) {
        this.idPerson = idPerson;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setIdGroup(Integer idGroup) {
        this.idGroup = idGroup;
    }
}
