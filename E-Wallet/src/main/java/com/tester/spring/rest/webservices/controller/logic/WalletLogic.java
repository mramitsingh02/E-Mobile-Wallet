package com.tester.spring.rest.webservices.controller.logic;

import com.tester.spring.rest.webservices.dto.ChangePinDTO;
import com.tester.spring.rest.webservices.dto.StatusChangeDTO;
import com.tester.spring.rest.webservices.dto.StatusDTO;
import com.tester.spring.rest.webservices.dto.WalletDTO;
import com.tester.spring.rest.webservices.exception.InvalidInputFoundException;
import com.tester.spring.rest.webservices.exception.NotFoundException;
import com.tester.spring.rest.webservices.exception.NotMatchingFoundException;
import com.tester.spring.rest.webservices.exception.WalletAlreadyException;
import com.tester.spring.rest.webservices.repository.pojo.ValidFor;
import com.tester.spring.rest.webservices.repository.type.StatusType;
import com.tester.spring.rest.webservices.utils.LocalIdGenerator;
import com.tester.spring.rest.webservices.utils.WalletIdGenerator;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.*;

@Slf4j
public class WalletLogic {

    private volatile static WalletLogic WALLET_LOGIC = new WalletLogic();

    public static WalletLogic getInstance() {
        return WALLET_LOGIC;
    }


    private void createWalletValidate(WalletDTO newWallet, WalletDTO deltaWallet) {
        if (nonNull(deltaWallet)) {
            log.error("{} msisdn already exist in system.", deltaWallet.getMsisdn());
            throw new WalletAlreadyException(deltaWallet.getMsisdn() + " msisdn already exist in system.");
        }
        createMandatoryFieldCheck(newWallet);
        if (nonNull(newWallet.getAmount())) {
            newWallet.getAmount().validate();
        }

    }

    private void createMandatoryFieldCheck(WalletDTO walletDTO) {
        if (isNull(walletDTO.getMsisdn())) {
            log.error("MSISDN not found.");
            throw new NotFoundException("MSISDN not found!");
        }

        if (isNull(walletDTO.getPin())) {
            log.error("PIN not found.");
            throw new NotFoundException("PIN not found!");
        }
    }

    public void createWallet(WalletDTO newWallet, WalletDTO deltaWallet) {
        createWalletValidate(newWallet, deltaWallet);
        prepareAndSetDefaultValue(newWallet);
    }

    private void prepareAndSetDefaultValue(WalletDTO walletDTO) {
        if (isNull(walletDTO.getWalletNumber())) {
            walletDTO.setWalletNumber(WalletIdGenerator.getInstance().generateId(walletDTO.getMsisdn(), walletDTO.getProvider()));
        }
        if (isNull(walletDTO.getCreatedTime())) {
            walletDTO.setCreatedTime(LocalDateTime.now());
        }
        if (isNull(walletDTO.getStatuses())) {
            StatusDTO availableStatus = new StatusDTO(StatusType.AVAILABLE, ValidFor.create());
            availableStatus.setWalletNumber(walletDTO.getWalletNumber());
            walletDTO.setStatuses(Collections.singletonList(availableStatus));
        }
        if (isNull(walletDTO.getWalletSpecId())) {
            walletDTO.setWalletSpecId("default-wallet-specId");
        }
        if (isNull(walletDTO.getPin())) {
            walletDTO.setRandom();
            walletDTO.setPinChanged(false);
        }
        if (isNull(walletDTO.getWalletNumber())) {
            walletDTO.setSupplierWalletNumber(LocalIdGenerator::getNewId);
        }

    }

    public void changePin(ChangePinDTO changePin, WalletDTO deltaWallet) {
        if (isNull(changePin)) {
            throw new NotFoundException("Provide change Request ");
        }
        if ((isNull(changePin.getConfirmPin()) || isNull(changePin.getNewPin()))) {
            throw new NotFoundException("Confirm Pin and New Pin should not be empty.");
        } else if (!Objects.equals(changePin.getConfirmPin(), changePin.getNewPin())) {
            throw new NotMatchingFoundException("Confirm Pin and New Pin not match.");
        }
        deltaWallet.setPin(changePin.getNewPin());
        deltaWallet.setPinChanged(true);
    }

    public void changeStatus(StatusChangeDTO changeStatus, WalletDTO deltaWallet) {
        validateChangeStatus(deltaWallet, changeStatus);
        updateDeltaWallet(deltaWallet, changeStatus);
    }

    private void updateDeltaWallet(WalletDTO deltaWallet, StatusChangeDTO changeStatus) {
        final List<StatusDTO> statuses = deltaWallet.getStatuses();
        final StatusLogic statusLogic = StatusLogic.getInstance();
        for (StatusDTO currentStatus : statuses) {
            final List<StatusType> walletStatus = statusLogic.getWalletStatus(currentStatus.getValue());
            final StatusDTO walletCurrentStatus = changeStatus.getStatuses();
            if (walletStatus.contains(walletCurrentStatus.getValue())) {
                currentStatus.setValue(walletCurrentStatus.getValue());
                if (walletCurrentStatus.getValidFor() != null) {
                    currentStatus.setValidFor(walletCurrentStatus.getValidFor());
                }
                break;
            }

        }
        deltaWallet.setUpdateTime(LocalDateTime.now());
    }

    private void validateChangeStatus(WalletDTO deltaWallet, StatusChangeDTO changeStatus) {
        final List<StatusDTO> statuses = deltaWallet.getStatuses();
        for (StatusDTO currentStatus : statuses) {
            if (currentStatus.isStatusSame(changeStatus.getStatuses().getValue())) {
                throw new InvalidInputFoundException("Status already " + changeStatus.getStatuses().getValue());
            }
        }


    }
}
