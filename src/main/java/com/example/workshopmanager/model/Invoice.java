package com.example.workshopmanager.model;

import com.example.workshopmanager.model.enums.VatRate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "invoices")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @UniqueElements
    private String invoiceNumber;
    private LocalDate dateOfInvoiceCreated;
    private VatRate VAT_RATE;
    @NotNull(message = "Owner can't be null")
    @OneToOne
    private Owner owner;
    @NotNull(message = "Car can't be null")
    @OneToOne
    private Car car;
    @NotNull(message = "Invoice must have at least one element")
    @ElementCollection
    private List<String> invoiceElements;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public LocalDate getDateOfInvoiceCreated() {
        return dateOfInvoiceCreated;
    }

    public void setDateOfInvoiceCreated() {
        dateOfInvoiceCreated = LocalDate.now();
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<String> getInvoiceElements() {
        return invoiceElements;
    }

    public void setInvoiceElements(List<String> invoiceElements) {
        this.invoiceElements = invoiceElements;
    }
}
