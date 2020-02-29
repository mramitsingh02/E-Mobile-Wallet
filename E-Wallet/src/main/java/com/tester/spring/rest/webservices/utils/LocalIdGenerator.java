package com.tester.spring.rest.webservices.utils;

import java.util.UUID;

public class LocalIdGenerator {
    public static String getNewId() {
        return UUID.randomUUID().toString();
    }

    public static UUID getUUId(String id) {
        return UUID.fromString(id);
    }

}
