package com.libraryManagementSystem.dto;

import com.libraryManagementSystem.model.Member;

import java.io.Serializable;
import java.time.LocalDate;

public class FineDTO implements Serializable {
    private Long id;
    private Long memberId;
    private double amount;
    private LocalDate dueDate;
    private boolean paidStatus;

    public FineDTO() {}

    public FineDTO(Long id, Long memberId, double amount, LocalDate dueDate, boolean paidStatus) {
        this.id = id;
        this.memberId = memberId;
        this.amount = amount;
        this.dueDate = dueDate;
        this.paidStatus = paidStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isPaidStatus() {
        return paidStatus;
    }

    public void setPaidStatus(boolean paidStatus) {
        this.paidStatus = paidStatus;
    }
}
