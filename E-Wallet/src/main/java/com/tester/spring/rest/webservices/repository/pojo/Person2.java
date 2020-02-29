package com.tester.spring.rest.webservices.repository.pojo;

import java.util.Date;

public class Person2 extends Person {
    private Name name;

    public Person2(Name name, Date dob) {
        super(dob);
        this.name = name;

    }

    public Person2(Name name) {
        super(null);
        this.name = name;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }
}
