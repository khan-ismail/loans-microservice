package com.zerotoismail.loans.repository;

import com.zerotoismail.loans.entity.LoansEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface LoansRepository extends JpaRepository<LoansEntity, Long> {
    Optional<LoansEntity> findByMobileNumber(String mobileNumber);
    Optional<LoansEntity> findByLoanNumber(String loanNumber);

    @Transactional
    @Modifying
    void deleteByMobileNumber(String mobileNumber);
}
