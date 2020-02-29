package com.tester.spring.rest.webservices.controller.logic;

import com.tester.spring.rest.webservices.repository.type.StatusType;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.tester.spring.rest.webservices.repository.type.StatusType.ACTIVE;
import static com.tester.spring.rest.webservices.repository.type.StatusType.AVAILABLE;
import static com.tester.spring.rest.webservices.repository.type.StatusType.DEACTIVATE;
import static com.tester.spring.rest.webservices.repository.type.StatusType.DELETED;
import static com.tester.spring.rest.webservices.repository.type.StatusType.SUSPENDED;

public class StatusLogic {

    private static final StatusLogic STATUS_LOGIC = new StatusLogic();
    private static Map<StatusType, List<StatusType>> walletStatusValidity = new LinkedHashMap<>();

    static {
        walletStatusValidity.put(AVAILABLE, Arrays.asList(ACTIVE, DEACTIVATE, SUSPENDED));
        walletStatusValidity.put(ACTIVE, Arrays.asList(DEACTIVATE, SUSPENDED, DELETED));
        walletStatusValidity.put(DEACTIVATE, Arrays.asList(ACTIVE, DELETED));
        walletStatusValidity.put(DELETED, Arrays.asList());
    }

    public static StatusLogic getInstance() {
        return STATUS_LOGIC;
    }

    public List<StatusType> getWalletStatus(StatusType currentStatus) {
        return walletStatusValidity.get(currentStatus);
    }


}
