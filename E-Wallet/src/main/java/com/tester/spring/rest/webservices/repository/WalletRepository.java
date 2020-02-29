package com.tester.spring.rest.webservices.repository;

import com.tester.spring.rest.webservices.repository.pojo.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, String> {

    Optional<Wallet> findByMsisdn(String msisdn);

}
