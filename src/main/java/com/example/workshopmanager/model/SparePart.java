package com.example.workshopmanager.model;

import com.example.workshopmanager.model.enums.VatRate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity(name = "spareParts")
//@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SparePart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String catalogNumber;
    @NotNull
    private String description;
    @NotNull
    private double buyPrice;
    //private ArrayList<Double> buyPrices;
    private double quantity = 0.0;
    //@NotNull
    private VatRate VAT_RATE;
    private String PKWiU;
}

