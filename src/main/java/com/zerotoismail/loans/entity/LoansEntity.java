package com.zerotoismail.loans.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Loans")
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class LoansEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    private String mobileNumber;

    private String loanNumber;

    private String loanType;

    private int totalLoan;

    private int amountPaid;

    private int outstandingAmount;

}