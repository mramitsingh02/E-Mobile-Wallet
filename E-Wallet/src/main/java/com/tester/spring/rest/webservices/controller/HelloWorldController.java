package com.tester.spring.rest.webservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping(path = "/hello")
    public String hello() {
        return "Welcome Amit Kumar Singh..";
    }


    @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
    public String helloWorld() {
        return messageSource.getMessage("message.user.register.success", null, LocaleContextHolder.getLocale());
    }

}
