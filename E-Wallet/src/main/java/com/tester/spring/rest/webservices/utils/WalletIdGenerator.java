package com.tester.spring.rest.webservices.utils;


import org.apache.commons.lang3.StringUtils;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Objects.*;

public class WalletIdGenerator implements IdGenerator {
    private static final String WALLET_FORMATTER = "%s-%s-%s-%s-%s";
    private static final DateTimeFormatter YYYY_MM_DD = DateTimeFormatter.ofPattern("uuMMddhhmmss");
    private static final AtomicInteger uniqueNode = new AtomicInteger(-1);
    private static final int BOUND = 999;
    private static final WalletIdGenerator WALLET_ID_GENERATOR = new WalletIdGenerator();


    private WalletIdGenerator() {

    }

    public static WalletIdGenerator getInstance() {
        return WALLET_ID_GENERATOR;
    }

    public static UUID getUUId(String id) {
        return UUID.fromString(id);
    }

    public static void main(String[] args) {


        final WalletIdGenerator walletIdGenerator = WalletIdGenerator.getInstance();
        for (int i = 0; i < 10000; i++) {
            System.out.println(walletIdGenerator.generateId("12345678", "120"));
        }
    }

    public String randomId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String generateId(String msisdn, String provider) {
        uniqueNode.incrementAndGet();
        if (uniqueNode.get() > 9999) {
            uniqueNode.set(0);
        }
        if (isNull(provider)) provider = "";
        if (isNull(msisdn)) msisdn = "";
        final String date = LocalDateTime.now().format(YYYY_MM_DD);
        String index1 = StringUtils.leftPad(provider, 3, "0");
        String index2 = StringUtils.leftPad(msisdn, 10, "0");
        String index3 = StringUtils.leftPad(date, 12, "0");
        String index4 = StringUtils.leftPad(String.valueOf(uniqueNode.get()), 4, "0");
        String index5 = StringUtils.leftPad(String.valueOf(Holder.getNext()), 3, "0");


        return String.format(WALLET_FORMATTER, index1, index2, index3, index4, index5);
    }

    private static class Holder {
        private static final SecureRandom numberGenerator = new SecureRandom();

        public static int getNext() {
            return numberGenerator.nextInt(BOUND);
        }
    }

}
