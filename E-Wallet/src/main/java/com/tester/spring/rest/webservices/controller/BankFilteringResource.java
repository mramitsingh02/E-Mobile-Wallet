package com.tester.spring.rest.webservices.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.tester.spring.rest.webservices.exception.AccountAuthenticationException;
import com.tester.spring.rest.webservices.repository.pojo.AccountDetails;
import com.tester.spring.rest.webservices.services.BankRespositoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BankFilteringResource {

    @Autowired
    private BankRespositoryServices bankRespositoryServices;

    @GetMapping("/balance/accountNumber/{accountNumber}/pin/{pin}")
    public MappingJacksonValue balance(@PathVariable String accountNumber, @PathVariable String pin) {

        AccountDetails self = bankRespositoryServices.load(accountNumber);
        if (self == null) {
            throw new AccountAuthenticationException("Account Not Exist");
        }
        if (self.getPin().equals(pin)) {
            MappingJacksonValue mappingJacksonValue = getMappingJacksonValue(self, RestConstants.ACCOUNT_DETAILS_FILTER, "accountHolder", "accountNumber", "balance");
            return mappingJacksonValue;
        }
        throw new AccountAuthenticationException("Account number and pin not exception.");
    }

    private MappingJacksonValue getMappingJacksonValue(AccountDetails self, String filterId, String... nameOfFilterAttributes) {
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(self);
        setFilterInMapping(filterId, mappingJacksonValue, nameOfFilterAttributes);
        return mappingJacksonValue;
    }

    private void setFilterInMapping(String filterId, MappingJacksonValue mappingJacksonValue, String[] nameOfFilterAttributes) {
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(nameOfFilterAttributes);
        FilterProvider filters = new SimpleFilterProvider().addFilter(filterId, filter);
        mappingJacksonValue.setFilters(filters);
    }

    private MappingJacksonValue getMappingJacksonValue(List<AccountDetails> accountDetails, String filterId, String... nameOfFilterAttributes) {
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(accountDetails);
        setFilterInMapping(filterId, mappingJacksonValue, nameOfFilterAttributes);
        return mappingJacksonValue;
    }

    @GetMapping(path = "/accounts")
    public MappingJacksonValue allBalances() {
        List<AccountDetails> lstOfAccountDetails = bankRespositoryServices.loadAll();
        MappingJacksonValue mappingJacksonValue = getMappingJacksonValue(lstOfAccountDetails, RestConstants.ACCOUNT_DETAILS_FILTER, "accountHolder", "accountNumber");
        return mappingJacksonValue;
    }


    //MappingJacksonValue mappingJacksonValue = new MappingJacksonValue();
    //ControllerLinkBuilder linkBuilder = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).balance("<AccountNumber>", "<PIN>"));
    //Resource<List<AccountDetails>> resource = new Resource<List<AccountDetails>>(lstOfAccountDetails, linkBuilder.withSelfRel());
    //resource.add(linkBuilder.withRel("balance enquiry"));


}
