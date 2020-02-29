package com.tester.spring.rest.webservices.dto;

import com.tester.spring.rest.webservices.repository.pojo.Amount;
import org.junit.Assert;
import org.junit.Test;


public class AmountTest {

    @Test
    public void test() {
        Amount amount = new Amount(11.5);
        Assert.assertEquals(amount.longValue(), 11.5);
    }

}