package com.example.workshopmanager.controller;

import com.example.workshopmanager.model.Car;
import com.example.workshopmanager.model.CarType;
import com.example.workshopmanager.model.EngineType;
import com.example.workshopmanager.repository.CarRepository;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("addCar")
public class CarAdd extends VerticalLayout {

    private final String ERROR_MESSAGE = "Nie zapisano samochodu. Popraw wymagane pola";
    private Car car;

    @Autowired
    public CarAdd(CarRepository carRepository) {

        FormLayout formLayout = new FormLayout();

        TextField carPlateField = new TextField();
        carPlateField.setLabel("Nr rejestracyjny");
        carPlateField.setRequired(true);


        TextField carBrandField = new TextField();
        carBrandField.setLabel("Marka");
        carBrandField.setRequired(true);
        carBrandField.setAutofocus(true);

        TextField carModelField = new TextField();
        carModelField.setLabel("Model");
        carModelField.setRequired(true);

        ComboBox<CarType> carTypeField = new ComboBox<>();
        carTypeField.setItems(CarType.values());
        carTypeField.setLabel("Rodzaj nadwozia");
        carTypeField.setRequired(true);

        TextField carBuildYear = new TextField();
        carBuildYear.setLabel("Rok produkcji");
        carBuildYear.setPattern("\\d{4}");
        carBuildYear.setRequired(true);

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
        carEngineTypeField.setRequired(true);

        Label invisibleLabel = new Label();

        Button saveButton = new Button("Zapisz", saveEvent -> {
            if(!carBrandField.getValue().isEmpty() && !carModelField.getValue().isEmpty() &&
                    !carPlateField.getValue().isEmpty() && !carTypeField.isEmpty() && !carBuildYear.getValue().isEmpty() &&
            !carEngineCapacityField.getValue().isEmpty() && !carEngineTypeField.isEmpty()) {
                Car car = new Car().builder()
                        .carBrand(carBrandField.getValue())
                        .carModel(carModelField.getValue())
                        .carType(carTypeField.getValue())
                        .plate(carPlateField.getValue().toUpperCase())
                        .productionDate(carBuildYear.getValue())
                        .vinNumber(carVinNumberField.getValue().toUpperCase())
                        .engineCapacity(carEngineCapacityField.getValue())
                        .maxPowerInKW(String.valueOf(carMaxPowerField.getValue()))
                        .engineType(carEngineTypeField.getValue())
                        .build();

                carRepository.save(car);

                Label content = new Label("Samochód o nr rej: " + car.getPlate() + " " + "został dodany do bazy");
                Notification notification = new Notification(content);
                notification.setDuration(4500);
                notification.setPosition(Notification.Position.MIDDLE);
                notification.getElement().attachShadow();
                notification.open();
            } else {
                Notification saveErrorNotification = new Notification(ERROR_MESSAGE, 4500,
                        Notification.Position.MIDDLE);
                saveErrorNotification.getElement().attachShadow();
                saveErrorNotification.open();
            }
        });

        Button cancleButton = new Button("Anuluj", cancleEvent -> {
//            editWindow.close();
        });

        formLayout.add(carBrandField, carModelField, carTypeField, carBuildYear, carVinNumberField, carPlateField,
                carEngineCapacityField, carMaxPowerField, carEngineTypeField, invisibleLabel, saveButton, cancleButton);

        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("21em", 2),
                new FormLayout.ResponsiveStep("22em", 3));

        add(formLayout);
    }
}
