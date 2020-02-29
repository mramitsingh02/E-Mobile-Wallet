package com.tester.spring.rest.webservices.repository.pojo;

import java.util.Date;

public class Person {
    protected Date dob;

    public Person(Date dob) {
        this.dob = dob;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
}
