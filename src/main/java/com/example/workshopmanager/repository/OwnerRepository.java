package com.example.workshopmanager.repository;

import com.example.workshopmanager.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {

//    List<Owner> findAllByOwnerNameOrOwnerSurnameOrOwnerNIPNumberOrOwnerPhoneNumberContains(String s);
    @Query(value = "SELECT o FROM owners o WHERE o.ownerName = ?1 and o.ownerSurname = ?2")
    Owner findByOwnerNameAndOwnerSurname(String name, String surname);

}
