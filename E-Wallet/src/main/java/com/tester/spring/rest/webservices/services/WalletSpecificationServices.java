package com.tester.spring.rest.webservices.services;

import com.tester.spring.rest.webservices.dto.WalletSpecificationDTO;
import com.tester.spring.rest.webservices.exception.WalletSpecificationNotFound;
import com.tester.spring.rest.webservices.repository.WalletSpecificationRepository;
import com.tester.spring.rest.webservices.repository.pojo.WalletSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WalletSpecificationServices {

    @Autowired
    private WalletSpecificationRepository walletSpecificationRepository;


    public WalletSpecificationDTO save(WalletSpecificationDTO walletSpecification) {
        return walletSpecificationRepository.save(walletSpecification.asPojo()).asDTO();
    }

    public WalletSpecificationDTO findById(String specId) {
        return walletSpecificationRepository.findById(specId).map(WalletSpecification::asDTO).orElseThrow(() -> new WalletSpecificationNotFound(specId + " Wallet Specification not found"));
    }

    public List<WalletSpecificationDTO> findByAll() {
        return walletSpecificationRepository.findAll().stream().map(WalletSpecification::asDTO).collect(Collectors.toList());
    }

    public WalletSpecificationDTO update(WalletSpecificationDTO walletSpecification) {
        final WalletSpecification byId = walletSpecificationRepository.findById(walletSpecification.getSpecId()).get();
        return walletSpecificationRepository.saveAndFlush(byId.merge(walletSpecification)).asDTO();
    }

    public List<WalletSpecificationDTO> updateAll(List<WalletSpecificationDTO> specifications) {
        return specifications.stream().map(this::update).collect(Collectors.toList());
    }
}
