package com.example.workshopmanager.repository;

import com.example.workshopmanager.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {

//    List<Owner> findAllByOwnerNameOrOwnerSurnameOrOwnerNIPNumberOrOwnerPhoneNumberContains(String s);


}
