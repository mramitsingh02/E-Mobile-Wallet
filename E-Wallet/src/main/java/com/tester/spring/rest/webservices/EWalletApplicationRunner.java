package com.tester.spring.rest.webservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

@SpringBootApplication
@ComponentScan(value = {
        "com.tester.spring.rest.webservices.services",
        "com.tester.spring.rest.webservices.repository",
        "com.tester.spring.rest.webservices.repository.pojo",
        "com.tester.spring.rest.webservices.controller",
        "com.tester.spring.rest.webservices.config"})
@EnableAutoConfiguration
public class EWalletApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(EWalletApplicationRunner.class, args);
        System.out.println("Started Successful............");
    }

    @Bean
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver acceptHeaderLocaleResolver = new AcceptHeaderLocaleResolver();
        acceptHeaderLocaleResolver.setDefaultLocale(Locale.US);
        return acceptHeaderLocaleResolver;
    }


    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        resourceBundleMessageSource.setBasename("messages");
        return resourceBundleMessageSource;
    }


}
