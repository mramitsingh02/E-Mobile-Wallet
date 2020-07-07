package com.tester.spring.rest.webservices.controller;

import com.tester.spring.rest.webservices.dto.WalletSpecificationDTO;
import com.tester.spring.rest.webservices.services.WalletSpecificationServices;
import com.tester.spring.rest.webservices.utils.LocalIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class WalletSpecificationController {


    @Autowired
    private WalletSpecificationServices walletSpecificationServices;


    @GetMapping("/walletSpecification/{specId}")
    public WalletSpecificationDTO get(@PathVariable String specId) {
        return walletSpecificationServices.findById(specId);
    }

    @GetMapping("/walletSpecification")
    public List<WalletSpecificationDTO> getAll() {
        return walletSpecificationServices.findByAll();
    }


    @PostMapping("/walletSpecification")
    public Resource<WalletSpecificationDTO> create(@Valid @RequestBody WalletSpecificationDTO walletSpecification) {
        walletSpecification.setSupplierSpecId(LocalIdGenerator::getNewId);
        final WalletSpecificationDTO specificationDTO = walletSpecificationServices.save(walletSpecification);

        Resource<WalletSpecificationDTO> resource = new Resource<>(specificationDTO);

        ControllerLinkBuilder linkBuilder = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).get(specificationDTO.getSpecId()));
        resource.add(linkBuilder.withRel("Self"));
        return resource;
    }

    @PutMapping("/walletSpecification")
    public WalletSpecificationDTO update(@Valid @RequestBody WalletSpecificationDTO walletSpecification) {
        return walletSpecificationServices.update(walletSpecification);
    }


    @PutMapping("/walletSpecifications")
    public List<WalletSpecificationDTO> updateAll(@Valid @RequestBody List<WalletSpecificationDTO> walletSpecification) {
        return walletSpecificationServices.updateAll(walletSpecification);
    }

}
