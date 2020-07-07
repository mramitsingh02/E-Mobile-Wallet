package com.tester.spring.rest.webservices.controller;

import com.tester.spring.rest.webservices.dto.ChangePinDTO;
import com.tester.spring.rest.webservices.dto.WalletDTO;
import com.tester.spring.rest.webservices.exception.AccountAuthenticationException;
import com.tester.spring.rest.webservices.mapper.MappingJacksonUtils;
import com.tester.spring.rest.webservices.services.WalletServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private WalletServices walletServices;


    @PostMapping("/")
    public MappingJacksonValue create(@Valid @RequestBody WalletDTO walletDTO) {
        final WalletDTO wallet = this.walletServices.save (walletDTO);
        return wallet.getMappingJacksonValue ();
    }

    @GetMapping(path = "/msisdn/{msisdn}")
    public MappingJacksonValue findByMsisdn(@PathVariable String msisdn) {
        WalletDTO wallet = this.walletServices.findByMsisdn (msisdn);
        return wallet.getMappingJacksonValue ();
    }


    @GetMapping(path = "/msisdn/{msisdn}/pin/{pin}")
    public MappingJacksonValue findByMsisdnAndPin(@PathVariable String msisdn, @PathVariable String pin) {
        WalletDTO wallet = this.walletServices.findByMsisdnAndPin (msisdn, pin);
        return wallet.getMappingJacksonValue ();
    }

    @PutMapping(path = "/msisdn/{msisdn}")
    public MappingJacksonValue changePin(@PathVariable String msisdn, @RequestBody ChangePinDTO changePin) {
        WalletDTO wallet = this.walletServices.changePin (msisdn, changePin);
        return wallet.getMappingJacksonValue ();
    }

    @GetMapping(path = "/All")
    public MappingJacksonValue findAll() {
        List<WalletDTO> wallets = this.walletServices.findAll ();
        return MappingJacksonUtils.getMappingJacksonValues (wallets);
    }

    @GetMapping("/walletNumber/{walletNumber}/pin/{pin}")
    public MappingJacksonValue getWalletByPin(@PathVariable String walletNumber, @PathVariable String pin) {
        WalletDTO wallet = this.walletServices.findByWalletNumber (walletNumber);
        if (wallet.getPin ().equals (pin)) return wallet.getMappingJacksonValue ();
        throw new AccountAuthenticationException ("Account number and pin not exception.");
    }

    @GetMapping("/walletNumber/{walletNumber}")
    public MappingJacksonValue getWallet(@PathVariable String walletNumber) {
        WalletDTO wallet = this.walletServices.findByWalletNumber (walletNumber);
        return wallet.getMappingJacksonValue ();
    }

    @DeleteMapping("/walletNumber/{walletNumber}")
    public void deleteByWallet(@PathVariable String walletNumber) {
        this.walletServices.deleteByWalletNumber (walletNumber);
    }


    @DeleteMapping(path = "/msisdn/{msisdn}/pin/{pin}")
    public void deleteByMsisdnAndPin(@PathVariable String msisdn, @PathVariable String pin) {
        this.walletServices.deleteByMsisdnAndPin (msisdn, pin);
    }


    @PostMapping("/wallet-v1")
    public Resource<WalletDTO> wallets(@Valid @RequestBody WalletDTO walletDTO) {
        final WalletDTO wallet = this.walletServices.save (walletDTO);
        return getWalletResource (wallet);
    }

    private Resource<WalletDTO> getWalletResource(WalletDTO wallet) {
        Resource<WalletDTO> resource = new Resource<> (wallet);
        Link linkBuilder = ControllerLinkBuilder.linkTo (ControllerLinkBuilder.methodOn (this.getClass ()).findByMsisdn (wallet.getMsisdn ())).withSelfRel ();
        resource.add (linkBuilder);
        return resource;
    }


}
