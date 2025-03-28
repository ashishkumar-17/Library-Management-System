package com.libraryManagementSystem.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
@DiscriminatorValue("Member")
public class Member extends User{

    @OneToMany(mappedBy = "borrowedBy")
    private List<BookItem> borrowedBooks;

    public Member(){}

    public Member(String name, String email, String phone) {
        super(name, email, phone);
    }

    public List<BookItem> getBorrowedBooks() { return borrowedBooks; }
    public void setBorrowedBooks(List<BookItem> borrowedBooks) { this.borrowedBooks = borrowedBooks; }

    @Override
    public String toString() {
        return "Member{} " + super.toString();
    }
}
