package com.libraryManagementSystem.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Librarian")
public class Librarian extends User {

    public Librarian(){}

    public Librarian(String name, String email, String phone) {
        super(name, email, phone);
    }

    @Override
    public String toString() {
        return "Librarian{} " + super.toString();
    }
}