package com.libraryManagementSystem.repository;


import com.libraryManagementSystem.model.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogRepository extends JpaRepository<Catalog, Long> {

    List<Catalog> findByCategoryIgnoreCase(String category);

    List<Catalog> findByAuthorContainingIgnoreCase(String author);

    List<Catalog> findByTitleContainingIgnoreCase(String title);

}