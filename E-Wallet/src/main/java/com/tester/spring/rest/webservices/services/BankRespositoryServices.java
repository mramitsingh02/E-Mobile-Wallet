package com.tester.spring.rest.webservices.services;

import com.tester.spring.rest.webservices.repository.pojo.AccountDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class BankRespositoryServices {
    private static Map<String, AccountDetails> userMap = new HashMap<>();
    private static AtomicInteger idGenerator = null;

    static {
        idGenerator = new AtomicInteger(10000000);
        AccountDetails bank1 = new AccountDetails("Amit Singh", String.valueOf(idGenerator.incrementAndGet()), "2043", 23480.0);
        AccountDetails bank2 = new AccountDetails("Ragwa Singh", String.valueOf(idGenerator.incrementAndGet()), "0443", 12000.0);
        AccountDetails bank3 = new AccountDetails("Mannu Singh", String.valueOf(idGenerator.incrementAndGet()), "2443", 20000.0);
        AccountDetails bank4 = new AccountDetails("Saroj Devi", String.valueOf(idGenerator.incrementAndGet()), "1000", 30000.0);
        userMap.put(bank1.getAccountNumber(), bank1);
        userMap.put(bank2.getAccountNumber(), bank2);
        userMap.put(bank3.getAccountNumber(), bank3);
        userMap.put(bank4.getAccountNumber(), bank4);
    }


    public AccountDetails save(AccountDetails accountDetails) {
        if (accountDetails == null) {
            return null;
        }
        accountDetails.setAccountNumber(String.valueOf(idGenerator.incrementAndGet()));
        userMap.put(accountDetails.getAccountNumber(), accountDetails);
        return accountDetails;
    }

    public AccountDetails update(AccountDetails accountDetails) {
        if (accountDetails == null) {
            return null;
        }
        accountDetails.setAccountNumber(String.valueOf(idGenerator.incrementAndGet()));
        userMap.put(accountDetails.getAccountNumber(), accountDetails);
        return accountDetails;
    }

    public AccountDetails load(String accountNumber) {
        if (accountNumber == null) {
            return null;
        }
        return userMap.get(accountNumber);
    }

    public AccountDetails delete(AccountDetails accountDetails) {
        if (accountDetails.getAccountNumber() == null) {
            return null;
        }
        return userMap.remove(accountDetails.getAccountNumber());
    }

    public List<AccountDetails> loadAll() {
        return new ArrayList<>(userMap.values());
    }
}
