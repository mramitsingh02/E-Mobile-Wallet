package com.tester.spring.rest.webservices.repository.pojo;

import java.util.Date;

public class Person1 extends Person {
    private String name;

    public Person1(String name, Date date) {
        super(date);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person1{" +
                "name='" + name + '\'' +
                ", dob=" + dob +
                '}';
    }
}
