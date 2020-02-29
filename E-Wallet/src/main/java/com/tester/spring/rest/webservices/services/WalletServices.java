package com.tester.spring.rest.webservices.services;

import com.tester.spring.rest.webservices.controller.logic.WalletLogic;
import com.tester.spring.rest.webservices.dto.ChangePinDTO;
import com.tester.spring.rest.webservices.dto.StatusChangeDTO;
import com.tester.spring.rest.webservices.dto.StatusDTO;
import com.tester.spring.rest.webservices.dto.WalletDTO;
import com.tester.spring.rest.webservices.exception.WalletNotFound;
import com.tester.spring.rest.webservices.repository.WalletRepository;
import com.tester.spring.rest.webservices.repository.pojo.Wallet;
import com.tester.spring.rest.webservices.repository.type.StatusType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Component
public class WalletServices {
    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private WalletSpecificationServices walletSpecificationServices;

    public WalletDTO findByWalletNumber(String walletNumber) {

        return walletRepository.findById(walletNumber).map(Wallet::asDTO).orElseThrow(() -> new WalletNotFound(walletNumber + " Wallet not found"));
    }


    public WalletDTO findByMsisdn(String msisdn) {
        final Supplier<WalletNotFound> walletNotFoundException = () -> new WalletNotFound("Wallet not found for " + msisdn);
        return walletRepository.findByMsisdn(msisdn).map(Wallet::asDTO).orElseThrow(walletNotFoundException);
        //return walletRepository.findOne(Example.of(Wallet.of(msisdn))).map(Wallet::asDTO).orElseThrow(() -> new WalletNotFound("Wallet not found for " + msisdn));
    }

    public Optional<WalletDTO> loadByMsisdn(String msisdn) {
        return findByWallet(Wallet.of(msisdn));
    }

    public WalletDTO save(WalletDTO walletDTO) {
        final Optional<WalletDTO> optionalWalletDTO = loadByMsisdn(walletDTO.getMsisdn());
        final WalletDTO deltaWallet = optionalWalletDTO.isPresent() ? optionalWalletDTO.get() : null;
        WalletLogic.getInstance().createWallet(walletDTO, deltaWallet);
        return walletRepository.save(walletDTO.asPojo()).asDTO();
    }

    public List<WalletDTO> findAll() {
        return walletRepository.findAll().stream().map(Wallet::asDTO).collect(Collectors.toList());
    }

    public WalletDTO findByMsisdnAndPin(String msisdn, String pin) {
        final Supplier<WalletNotFound> walletNotFoundException = () -> new WalletNotFound("Wallet not found for " + msisdn);
        return findByWallet(Wallet.of(msisdn, pin)).orElseThrow(walletNotFoundException);
    }

    public Optional<WalletDTO> findByWallet(Wallet wallet) {
        return walletRepository.findOne(Example.of(wallet)).map(Wallet::asDTO);
    }

    public WalletDTO changePin(String msisdn, ChangePinDTO changePin) {
        final WalletDTO deltaWallet = findByMsisdnAndPin(changePin.getMsisdn(), changePin.getOldPin());
        WalletLogic.getInstance().changePin(changePin, deltaWallet);
        return walletRepository.saveAndFlush(deltaWallet.asPojo()).asDTO();
    }

    public WalletDTO changeChangeStatus(String msisdn, StatusChangeDTO changeStatus) {
        final WalletDTO deltaWallet = findByMsisdn(msisdn);
        WalletLogic.getInstance().changeStatus(changeStatus, deltaWallet);
        return walletRepository.saveAndFlush(deltaWallet.asPojo()).asDTO();
    }

    public WalletDTO changeChangeStatus(String msisdn, String changeStatus) {
        final WalletDTO deltaWallet = findByMsisdn(msisdn);
        StatusDTO status = new StatusDTO(StatusType.valueOf(changeStatus), null);
        StatusChangeDTO changeStatusDTO = new StatusChangeDTO(deltaWallet.getWalletNumber(), deltaWallet.getMsisdn(), status);
        WalletLogic.getInstance().changeStatus(changeStatusDTO, deltaWallet);
        return walletRepository.saveAndFlush(deltaWallet.asPojo()).asDTO();
    }

    public void deleteByMsisdnAndPin(String msisdn, String pin) {
        final Supplier<WalletNotFound> walletNotFoundException = () -> new WalletNotFound("Wallet not found for " + msisdn);
        final WalletDTO walletDTO = findByWallet(Wallet.of(msisdn, pin)).orElseThrow(walletNotFoundException);
        walletRepository.deleteById(walletDTO.getWalletNumber());


    }

    public void deleteByWalletNumber(String walletNumber) {
        final Supplier<WalletNotFound> walletNotFoundException = () -> new WalletNotFound("Wallet not found for " + walletNumber);
        final WalletDTO walletDTO = findByWallet(Wallet.ofWalletNumber(walletNumber)).orElseThrow(walletNotFoundException);
        walletRepository.deleteById(walletDTO.getWalletNumber());
    }
}
