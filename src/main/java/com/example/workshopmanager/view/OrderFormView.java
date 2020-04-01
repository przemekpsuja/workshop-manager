package com.example.workshopmanager.view;

import com.example.workshopmanager.controller.RepairOrderController;
import com.example.workshopmanager.model.Car;
import com.example.workshopmanager.model.Owner;
import com.example.workshopmanager.model.RepairOrder;
import com.example.workshopmanager.model.StatusOrder;
import com.example.workshopmanager.repository.CarRepository;
import com.example.workshopmanager.repository.OwnerRepository;
import com.example.workshopmanager.repository.RepairOrderRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@Route("addOrder")
public class OrderFormView extends FormLayout {

    private RepairOrder repairOrder;
    private OwnerRepository ownerRepository;
    private CarRepository carRepository;
    private RepairOrderRepository repairOrderRepository;
    private RepairOrderController repairOrderController;


    public OrderFormView(OwnerRepository ownerRepository, RepairOrderRepository repairOrderRepository, CarRepository carRepository, RepairOrderController repairOrderController) {
        ComboBox<Owner> ownerPicker = new ComboBox<>();
        List<Owner> ownerList = new ArrayList<>(ownerRepository.findAll());
        ownerPicker.setItems(ownerList);
        ownerPicker.setItemLabelGenerator(Owner::getOwnerFullName);
        ownerPicker.setLabel("Zlecający");
        ComboBox<Car> carPicker = new ComboBox<>();
        List<Car> carsList = new ArrayList<>(carRepository.findAll());
        carPicker.setItems(carsList);
        carPicker.setItemLabelGenerator(Car::getPlate);
        carPicker.setLabel("Samochód");
//        Label label1 = new Label();
//        label1.setText("Tel. " + ownerPicker.getValue().getOwnerPhoneNumber());
//        Label label2= new Label();
//        label2.setText(carPicker.getValue().getCarBrand() + " " + carPicker.getValue().getCarModel() + ", rok: "
//        + carPicker.getValue().getProductionDate() + ", moc: " + carPicker.getValue().getMaxPowerInKW() + "kW, VIN: "
//        + carPicker.getValue().getVinNumber());
        VerticalLayout vl = new VerticalLayout();
        TextField carBrand = new TextField();
        carBrand.setReadOnly(true);
        TextField carModel = new TextField();
        carModel.setReadOnly(true);
        TextField carYear = new TextField();
        carYear.setReadOnly(true);
        TextField carType = new TextField();
        carType.setReadOnly(true);
        TextField carPlate = new TextField();
        carPlate.setReadOnly(true);
        TextField carVin = new TextField();
        carVin.setReadOnly(true);
        TextField carEngineCapacity = new TextField();
        carEngineCapacity.setReadOnly(true);
        TextField carEngineType = new TextField();
        carEngineType.setReadOnly(true);
        TextField carPower = new TextField();
        carPower.setReadOnly(true);
        TextArea description = new TextArea();
        description.setMinWidth("800px");
        description.setHeight("350px");
        description.setLabel("Opis zlecenia");
        Button saveButton = new Button("Zapisz", buttonClickEvent -> {
            RepairOrder order = new RepairOrder();
            order.setLocalDateTime();
            order.setStatus(StatusOrder.OCZEKUJE);
            order.setOrderNumber(repairOrderController.orderNumberGenerator());
            order.setOwner(ownerRepository.findByOwnerNameAndOwnerSurname(ownerPicker.getValue().getOwnerName(),
                    ownerPicker.getValue().getOwnerSurname()));
            order.setCar(carRepository.findByPlate(carPicker.getValue().getPlate()));
            order.setDescription(description.getValue());
            repairOrderRepository.save(order);
        });
        vl.add(description, saveButton);
        add(ownerPicker, carPicker, vl);
    }
}
