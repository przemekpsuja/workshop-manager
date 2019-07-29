package com.example.workshopmanager.controller;

import com.example.workshopmanager.model.Car;
import com.example.workshopmanager.model.EngineType;
import com.example.workshopmanager.repository.CarRepository;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("addCar")
public class CarAdd extends VerticalLayout {

    private Car car;

    @Autowired
    public CarAdd(CarRepository carRepository) {

        FormLayout formLayout = new FormLayout();

        TextField carPlateField = new TextField();
        carPlateField.setLabel("Nr rejestracyjny");
        carPlateField.setRequired(true);
        carPlateField.setAutofocus(true);

        TextField carBrandField = new TextField();
        carBrandField.setLabel("Marka");
        carBrandField.setRequired(true);

        TextField carModelField = new TextField();
        carModelField.setLabel("Model");
        carModelField.setRequired(true);

        TextField carBuildYear = new TextField();
        carBuildYear.setLabel("Rok produkcji");
        carBuildYear.setPattern("\\d{4}");

        TextField carVinNumberField = new TextField();
        carVinNumberField.setLabel("VIN");
        carVinNumberField.setPattern("[A-HJ-NPR-Z0-9]{17}");

        TextField carEngineCapacityField = new TextField();
        carEngineCapacityField.setLabel("Poj. silnika");
        carEngineCapacityField.setRequired(true);

        TextField carMaxPowerField = new TextField();
        carMaxPowerField.setLabel("Moc silnika (kW)");

        ComboBox<EngineType> carEngineTypeField = new ComboBox<>();
        carEngineTypeField.setItems(EngineType.values());
        carEngineTypeField.setLabel("Rodzaj silnika");

        formLayout.add(carBrandField, carModelField, carBuildYear, carVinNumberField, carPlateField,
                carEngineCapacityField, carMaxPowerField, carEngineTypeField );




        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("21em", 2),
                new FormLayout.ResponsiveStep("22em", 3));


        add(formLayout);
    }
}
