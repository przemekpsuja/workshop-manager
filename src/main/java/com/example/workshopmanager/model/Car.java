package com.example.workshopmanager.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.lang.String;

@Entity(name = "cars")
@Setter
@Getter
@Builder
public class Car {

    @Id
    private String plate;
    private Date firstRegistrationDate;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
    private String carBrand;
    private String carType;
    private String carModel;
    private String vinNumber;
    private int maxCarWeight;
    private int accetableCarWeight;
    private int accetableCarSetWeight;
    private int carOwnWeight;
    private String dateOfExpiryRegistrationCertificate;
    private String dateOfIssuingOfRegistrationCertificate;
    private String vehiceCategory;
    private String approvalCertificateNumber;
    private int numberOfAxles;
    private int maxWeightOfTrailerWithBrakes;
    private int maxWeightOfTrailerWithoutBrakes;
    private int engineCapacity;
    private int maxPowerInKW;
    private int numberOfSeats;
    private int numberOfStandingPlaces;
    private String dateOfNextDiagnosticInspection;

}
