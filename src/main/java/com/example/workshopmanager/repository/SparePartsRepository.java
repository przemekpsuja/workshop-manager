package com.example.workshopmanager.repository;

import com.example.workshopmanager.model.SparePart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SparePartsRepository extends JpaRepository<SparePart, Long> {

    SparePart findByCatalogNumberContains(String catalogNumber);
    SparePart findById(long id);
}
