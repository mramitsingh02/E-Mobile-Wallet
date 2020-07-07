package com.tester.spring.rest.webservices.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MaskStringTest {


    private MaskString maskString;

    @Test
    public void shouldThrowExceptionForEmptyString() {

        System.out.println(this.getClass().getSimpleName());

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new MaskString("");
        });
    }

    @Test
    public void shouldThrowExceptionForNullString() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            final String value = null;
            new MaskString(value);
        });
    }

    @Test
    public void shouldMaskString() {
        maskString = new MaskString("Amit Kumar");
        Assertions.assertEquals("xxxxxxxxar", maskString.toString());
    }


    @Test
    public void shouldMaskStringWithNumberOfPostIndex() {
        maskString = new MaskString("Amit Kumar Singh", 7);
        Assertions.assertEquals("xxxxxxxxxr Singh", maskString.toString());
    }

    @Test
    public void shouldUnMaskStringW() {
        maskString = new MaskString("Amit Kumar Singh");
        Assertions.assertEquals("Amit Kumar Singh", maskString.unMaskString());
    }


}