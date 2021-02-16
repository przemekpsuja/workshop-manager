package com.example.workshopmanager.model;

import com.example.workshopmanager.model.enums.StatusOrder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
@Entity(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
public class RepairOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime localDateTime;
    private String orderNumber;
    @ManyToOne
    private Owner owner;
    @ManyToOne
    private Car car;
    private StatusOrder status;
    private String description = "";



    public Long getId() {
        return id;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime() {
        localDateTime = localDateTime.now();
    }

    public Optional<Owner> getOwner() {
        return Optional.ofNullable(owner);
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getOwnerFullname() {
        return owner.getOwnerFullName();
    }

    public Car getCar() {
        return car;
    }

    public String getCarPlate() {
        return car.getPlate();
    }

    public String getCarBrand() {
        return car.getCarBrand();
    }

    public String getCarModel() {
        return car.getCarModel();
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public StatusOrder getStatus() {
        return status;
    }

    public void setStatus(StatusOrder status) {
        this.status = status;
    }
}
