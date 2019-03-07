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
}
