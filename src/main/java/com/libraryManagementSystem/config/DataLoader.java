package com.libraryManagementSystem.config;

import com.libraryManagementSystem.model.*;

import com.libraryManagementSystem.repository.BookItemRepository;
import com.libraryManagementSystem.repository.BookRepository;
import com.libraryManagementSystem.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final BookItemRepository bookItemRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public DataLoader(BookRepository bookRepository,
                      BookItemRepository bookItemRepository,
                      MemberRepository memberRepository){
        this.bookRepository = bookRepository;
        this.bookItemRepository = bookItemRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public void run(String... args) {
        // Adding Books
        Book book1 = new Book("Effective Java", "Joshua Bloch","1234","Java", 1995,10);
        Book book2 = new Book("Clean Code", "Robert C. Martin", "1235", "Code", 2000, 15);
        Book book3 = new Book("Spring in Action", "Craig Walls", "1236", "Spring", 2003, 2);

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);

        // Adding Book Items
        BookItem item1 = new BookItem(1L, book1, BookItemStatus.AVAILABLE);
        BookItem item2 = new BookItem(2L, book2, BookItemStatus.AVAILABLE);
        BookItem item3 = new BookItem(3L, book3, BookItemStatus.AVAILABLE);

        bookItemRepository.save(item1);
        bookItemRepository.save(item2);
        bookItemRepository.save(item3);

        // Adding Members
        Member member1 = new Member("Alice Johnson", "alice@example.com", "8459452896");
        Member member2 = new Member("Bob Williams", "bob@example.com", "8654752165");

        memberRepository.save(member1);
        memberRepository.save(member2);
    }
}
