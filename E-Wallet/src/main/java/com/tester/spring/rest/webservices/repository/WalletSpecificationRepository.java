package com.tester.spring.rest.webservices.repository;

import com.tester.spring.rest.webservices.repository.pojo.WalletSpecification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletSpecificationRepository extends JpaRepository<WalletSpecification, String> {
}
