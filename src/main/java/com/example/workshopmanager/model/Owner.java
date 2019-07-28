package com.example.workshopmanager.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Component
@Entity(name = "owners")
@Setter
@Getter
public class Owner {

    @Id
    @GeneratedValue
    private long id;
    private String ownerName;
    private String ownerSurname;
    private String ownerAdressStreet;
    private String ownerAdressCity;
    private String OwnerAdressZipCode;
    private long ownerPhoneNumber;
    private String ownerEmail;
    private boolean isOwnerACompany;
    private long ownerNIPNumber;
    @OneToMany(mappedBy = "owner")
    private List<Car> cars;
}
