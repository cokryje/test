package com.example.springpost;

import java.util.Date;

public class Employee {
    private String name;
    private String surname;
    private Date employmentDate;

    public Employee(String name, String surname, Date employmentDate) {
        this.name = name;
        this.surname = surname;
        this.employmentDate = employmentDate;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Date getEmploymentDate() {
        return employmentDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmploymentDate(Date employmentDate) {
        this.employmentDate = employmentDate;
    }
}
