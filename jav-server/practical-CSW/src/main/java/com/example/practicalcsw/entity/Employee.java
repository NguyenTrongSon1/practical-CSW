package com.example.practicalcsw.entity;

public class Employee {
    private int id;
    private String name;
    private String email;
    private int status;

    public Employee() {
    }

    public int getId() {
        return id;
    }

    public Employee(int id, String name, String email, int status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.status = status;
    }

    public Employee(String name, String email, int status) {
        this.name = name;
        this.email = email;
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
