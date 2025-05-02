package com.test.diego.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private Integer termMonths;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoanStatus status = LoanStatus.PENDING;

    @Column(nullable = false)
    private Long userId;

    public Loan() {}

    public Loan(BigDecimal amount, Integer termMonths, Long userId) {
        this.amount = amount;
        this.termMonths = termMonths;
        this.userId = userId;
    }

    // getters / setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public Integer getTermMonths() { return termMonths; }
    public void setTermMonths(Integer termMonths) { this.termMonths = termMonths; }

    public LoanStatus getStatus() { return status; }
    public void setStatus(LoanStatus status) { this.status = status; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}
