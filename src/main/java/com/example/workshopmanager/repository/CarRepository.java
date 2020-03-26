package com.example.workshopmanager.repository;

import com.example.workshopmanager.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository <Car, String> {

    Car findByPlate(final String plate);
    Car findByVinNumber(final String vinNumber);
    Optional<Car> findByOwner(final String owner);
    Optional<Car> findByProductionDate (final String productionDate);
}
