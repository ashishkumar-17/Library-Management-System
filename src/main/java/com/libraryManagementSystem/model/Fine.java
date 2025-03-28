package com.libraryManagementSystem.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Fine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private double amount;
    private LocalDate dueDate;
    private boolean paidStatus;

    public Fine() {}

    public Fine(Member member, double amount, LocalDate dueDate, boolean paidStatus){
        this.member = member;
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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
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

    @Override
    public String toString() {
        return "Fine{" +
                "id=" + id +
                ", member=" + member +
                ", amount=" + amount +
                ", dueDate=" + dueDate +
                ", paidStatus=" + paidStatus +
                '}';
    }
}
