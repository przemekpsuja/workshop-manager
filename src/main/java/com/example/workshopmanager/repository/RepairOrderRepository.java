package com.example.workshopmanager.repository;

import com.example.workshopmanager.model.RepairOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepairOrderRepository extends JpaRepository<RepairOrder, Long> {

    @Query(value = "SELECT order_number FROM orders WHERE orders.id = (SELECT MAX(orders.id) FROM orders)", nativeQuery = true)
    String findLastOrderNumber();

}
