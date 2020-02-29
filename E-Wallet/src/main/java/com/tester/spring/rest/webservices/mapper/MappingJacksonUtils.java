package com.tester.spring.rest.webservices.mapper;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.tester.spring.rest.webservices.dto.WalletDTO;
import org.springframework.http.converter.json.MappingJacksonValue;

import java.util.List;

import static com.tester.spring.rest.webservices.controller.RestConstants.WALLET_DETAILS_FILTER;
import static com.tester.spring.rest.webservices.controller.RestConstants.WALLET_FIELDS;

public class MappingJacksonUtils {

    public static void setFilterInMapping(String filterId, MappingJacksonValue mappingJacksonValue, String[] nameOfFilterAttributes) {
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(nameOfFilterAttributes);
        FilterProvider filters = new SimpleFilterProvider().addFilter(filterId, filter);
        mappingJacksonValue.setFilters(filters);
    }

    public static MappingJacksonValue getMappingJacksonValues(List<WalletDTO> accountDetails) {
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(accountDetails);
        setFilterInMapping(WALLET_DETAILS_FILTER, mappingJacksonValue, WALLET_FIELDS);
        return mappingJacksonValue;
    }
}
