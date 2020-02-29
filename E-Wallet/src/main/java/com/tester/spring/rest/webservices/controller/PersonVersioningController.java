package com.tester.spring.rest.webservices.controller;

import com.tester.spring.rest.webservices.repository.pojo.Name;
import com.tester.spring.rest.webservices.repository.pojo.Person;
import com.tester.spring.rest.webservices.repository.pojo.Person1;
import com.tester.spring.rest.webservices.repository.pojo.Person2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class PersonVersioningController {

    @GetMapping("/person/v1")
    public Person personV1() {
        return new Person1("Amit Kumar Singh", new Date());
    }

    @GetMapping("/person/v2")
    public Person personV2() {
        return new Person2(new Name("Amit", "Kumar", "Singh"), new Date());
    }


    @GetMapping(path = "/person/param", params = "version=1")
    public Person requestParamV1() {
        return new Person1("Amit Kumar Singh", new Date());
    }

    @GetMapping(path = "/person/param", params = "version=2")
    public Person requestParamV2() {
        return new Person2(new Name("Amit", "Kumar", "Singh"), new Date());
    }

    @GetMapping(path = "/person/headers", headers = "X-API-VERSION=1")
    public Person headerParamV1() {
        return new Person1("Amit Kumar Singh", new Date());
    }

    @GetMapping(path = "/person/headers", headers = "X-API-VERSION=2")
    public Person headerParamV2() {
        return new Person2(new Name("Amit", "Kumar", "Singh"), new Date());
    }

    @GetMapping(path = "/person/produces", produces = "application/vnd.company.app-v1+json")
    public Person producesV1() {
        return new Person1("Amit Kumar Singh", new Date());
    }

    @GetMapping(path = "/person/produces", produces = "application/vnd.company.app-v2+json")
    public Person producesV2() {
        return new Person2(new Name("Amit", "Kumar", "Singh"), new Date());
    }


}
