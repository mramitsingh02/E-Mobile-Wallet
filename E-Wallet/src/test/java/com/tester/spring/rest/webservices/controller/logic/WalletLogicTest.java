package com.tester.spring.rest.webservices.controller.logic;

import com.tester.spring.rest.webservices.controller.facory.ChangeFactory;
import com.tester.spring.rest.webservices.controller.facory.StatusChangeFactory;
import com.tester.spring.rest.webservices.controller.facory.WalletFactory;
import com.tester.spring.rest.webservices.dto.ChangePinDTO;
import com.tester.spring.rest.webservices.dto.StatusChangeDTO;
import com.tester.spring.rest.webservices.dto.WalletDTO;
import com.tester.spring.rest.webservices.repository.type.StatusType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WalletLogicTest {
    private WalletLogic instance;

    @BeforeEach
    public void setUp() {
        instance = WalletLogic.getInstance();

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void getInstance() {
        WalletLogic instance2 = WalletLogic.getInstance();
        assertEquals(instance, instance2);
        assertEquals(instance.hashCode(), instance2.hashCode());
    }


    @Test
    public void createWallet() {
        WalletDTO newWallet = WalletFactory.newWalletWithMandatoryField();
        WalletDTO deltaWallet = null;
        instance.createWallet(newWallet, deltaWallet);
        assertEquals(1, newWallet.getStatuses().size());
        assertNotNull(newWallet.getWalletNumber());
        assertNotNull(newWallet.getWalletSpecId());
        assertNotNull(newWallet.getCreatedTime());
        assertNotNull(newWallet.asPojo());
        assertNotNull(newWallet.asPojo().getAmount());
        assertNotNull(newWallet.getAmount());
        assertEquals(newWallet.getAmount(), newWallet.asPojo().getAmount());
        assertEquals(0, newWallet.getAmount().intValue());
    }

    @Test
    public void changePin() {
        WalletDTO deltaWallet = WalletFactory.newWalletWithAllField();
        ChangePinDTO changePin = ChangeFactory.newChangePinWithMandatoryField(deltaWallet.getMsisdn(), deltaWallet.getPin());
        instance.changePin(changePin, deltaWallet);
        assertNotEquals(deltaWallet.getPin(), changePin.getOldPin());
        assertEquals(deltaWallet.getPin(), changePin.getConfirmPin());
        assertEquals(deltaWallet.getPin(), changePin.getNewPin());
        assertEquals(deltaWallet.getMsisdn(), changePin.getMsisdn());
    }

    @Test
    public void changeStatus() {
        WalletDTO deltaWallet = WalletFactory.newWalletWithAllField();
        StatusChangeDTO statusChange = StatusChangeFactory.newStatusChangeWithMandatoryField(deltaWallet.getMsisdn(), StatusType.ACTIVE);
        instance.changeStatus(statusChange, deltaWallet);
        assertEquals(statusChange.getMsisdn(), deltaWallet.getMsisdn());
        assertNotNull(statusChange.getStatuses());
        assertTrue(deltaWallet.getStatuses().contains(statusChange.getStatuses()));


    }
}