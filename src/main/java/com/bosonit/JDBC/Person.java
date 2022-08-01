package com.bosonit.JDBC;

public class Person {
    private long id;
    private String name;
    private String surname;
    private boolean admin;
    public Person() {
    }

    public Person(long id, String name, String surname, boolean admin) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.admin = admin;
    }
    public Person(String name, String surname, boolean admin) {
        this.name = name;
        this.surname = surname;
        this.admin = admin;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public boolean isAdmin() {
        return admin;
    }
    public void setAdmin(boolean isAdmin) {
        this.admin = isAdmin;
    }
    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name + ", surname=" + surname + ", admin=" + admin + "]";
    }
}
