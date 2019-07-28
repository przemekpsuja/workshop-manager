package com.example.workshopmanager.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "owners")
@Setter
@Getter
@Builder
@RequiredArgsConstructor
//@NoArgsConstructor
@AllArgsConstructor
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ownerName;
    private String ownerSurname;
    private String ownerAdressStreet;
    private String ownerAdressCity;
    private String ownerAdressZipCode;
    private String ownerPhoneNumber;
    private String ownerEmail;
    private boolean isOwnerACompany;
    private String ownerNIPNumber;
    @OneToMany(mappedBy = "owner")
    private List<Car> cars;


}
