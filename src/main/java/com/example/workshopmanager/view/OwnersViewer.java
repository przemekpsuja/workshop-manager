package com.example.workshopmanager.view;

import com.example.workshopmanager.controller.OwnerAdder;
import com.example.workshopmanager.model.Car;
import com.example.workshopmanager.model.Owner;
import com.example.workshopmanager.repository.CarRepository;
import com.example.workshopmanager.repository.OwnerRepository;
import com.example.workshopmanager.services.CarService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.contextmenu.GridContextMenu;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Route("allOwners")
public class OwnersViewer extends VerticalLayout {

    private OwnerAdder ownerAdder;
    private OwnerRepository ownerRepository;
    private CarRepository carRepository;
    private CarService carService;

    @Autowired
    public OwnersViewer(OwnerRepository ownerRepository, CarRepository carRepository, CarService carService) {
        this.ownerRepository = ownerRepository;
        this.carRepository = carRepository;
        this.carService = carService;

        Grid<Owner> grid = new Grid<>(Owner.class);
        grid.getColumnByKey("id").setWidth("30px");
        Grid.Column<Owner> ownerNameColumn = grid.addColumn(Owner::getOwnerName).setHeader("Imię");
        grid.addColumn(Owner::getOwnerSurname).setHeader("Nazwisko");
        grid.addColumn(Owner::getOwnerAdressStreet).setHeader("Ulica");
        grid.addColumn(Owner::getOwnerAdressCity).setHeader("Miasto");
        grid.addColumn(Owner::getOwnerAdressZipCode).setHeader("Kod pocztowy");
        grid.addColumn(Owner::getOwnerPhoneNumber).setHeader("Telefon");
        grid.addColumn(Owner::getOwnerEmail).setHeader("E-mail");
        grid.addColumn(Owner::getOwnerNIPNumber).setHeader("NIP");
        grid.setItems(ownerRepository.findAll());

        GridContextMenu<Owner> contextMenu = new GridContextMenu<>(grid);
        contextMenu.addItem("Edytuj", event -> {
            event.getItem().ifPresent(owner -> {
                Dialog editWindow = new Dialog();
                editWindow.setWidth("800px");
                editWindow.setCloseOnEsc(true);
                editWindow.setCloseOnOutsideClick(false);

                FormLayout editForm = new FormLayout();
                editForm.setResponsiveSteps(
                        new FormLayout.ResponsiveStep("25em", 1),
                        new FormLayout.ResponsiveStep("32em", 2)
                );

                TextField clientName = new TextField();
                clientName.setAutofocus(true);
                clientName.setLabel("Imię: ");
                clientName.setRequired(true);
                clientName.setSizeUndefined();
                clientName.setValue(owner.getOwnerName());

                TextField clientSurname = new TextField();
                clientSurname.setLabel("Nazwisko: ");
                clientSurname.setRequired(true);
                clientSurname.setValue(owner.getOwnerSurname());
                clientSurname.setWidth("200px");

                TextField clientAdressStreet = new TextField();
                clientAdressStreet.setLabel("Ulica: ");
                clientAdressStreet.setValue(owner.getOwnerAdressStreet());

                TextField clientAdressCity = new TextField();
                clientAdressCity.setLabel("Miasto: ");
                clientAdressCity.setValue(owner.getOwnerAdressCity());

                TextField clientAdressZipCode = new TextField();
                clientAdressZipCode.setLabel("Kod pocztowy: ");
                clientAdressZipCode.setPattern("\\d{2}-\\d{3}");
                clientAdressZipCode.setValue(owner.getOwnerAdressZipCode());

                TextField clientPhoneNumber = new TextField();
                clientPhoneNumber.setLabel("Nr tel: ");
                clientPhoneNumber.setRequired(true);
                clientPhoneNumber.setValue(owner.getOwnerPhoneNumber());

                TextField clientEmail = new TextField();
                clientEmail.setLabel("E-mail: ");
                clientEmail.setPattern("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b");
                clientEmail.setValue(owner.getOwnerEmail());

                //TODO Nie działa do końca tak jak powinno
                TextField clientNIPNumber = new TextField();
                clientNIPNumber.setLabel("NIP: ");
//                clientNIPNumber.setReadOnly(true);
//                if (owner.getOwnerNIPNumber() != "") {
//                    clientNIPNumber.setValue(owner.getOwnerNIPNumber());
//                    isClientACompany.setValue(true);
//                } else {
//                    clientNIPNumber.setVisible(false);
//                    isClientACompany.setValue(false);
//                }

                Checkbox isClientACompany = new Checkbox();
                isClientACompany.setLabel("Czy klient jest firmą");
                isClientACompany.setValue(owner.isOwnerACompany());
                isClientACompany.addValueChangeListener(changeEvent -> {
                    if(isClientACompany.getValue() == true){
                        clientNIPNumber.setVisible(true);
                        clientNIPNumber.setValue(owner.getOwnerNIPNumber());
                    } else {
                        clientNIPNumber.setVisible(false);
                    }
                });


//                isClientACompany.addValueChangeListener(changeEvent -> {
//                    if (!isClientACompany.getValue()) {
//                        clientNIPNumber.setVisible(false);
//
//                    } else {
//                        clientNIPNumber.setVisible(true);
//                        clientNIPNumber.setReadOnly(false);
//                    }
//                });

                HorizontalLayout horizontalLayout = new HorizontalLayout();

                if (!owner.getCars().isEmpty()) {
                    Grid<Car> ownerCars = new Grid<>();
                    ownerCars.setHeightByRows(true);
                    ownerCars.addColumn(Car::getPlate).setHeader("Nr rej");
                    ownerCars.addColumn(Car::getCarBrand).setHeader("Producent");
                    ownerCars.addColumn(Car::getCarModel).setHeader("Model");
                    ownerCars.addColumn(Car::getVinNumber).setHeader("VIN");
                    ownerCars.setItems(owner.getCars());
                    horizontalLayout.add(ownerCars);
                    horizontalLayout.setPadding(true);

                }

                Label invisibleLabel = new Label();

                Button saveButton = new Button("Zapisz", saveEvent -> {
                    owner.setOwnerName(clientName.getValue());
                    owner.setOwnerSurname(clientSurname.getValue());
                    owner.setOwnerAdressStreet(clientAdressStreet.getValue());
                    owner.setOwnerAdressCity(clientAdressCity.getValue());
                    owner.setOwnerAdressZipCode(clientAdressZipCode.getValue());
                    owner.setOwnerPhoneNumber(clientPhoneNumber.getValue());
                    owner.setOwnerEmail(clientEmail.getValue());
                    owner.setOwnerACompany(isClientACompany.getValue());
                    owner.setOwnerNIPNumber(clientNIPNumber.getValue());

                    ownerRepository.save(owner);
                    grid.setItems(ownerRepository.findAll());
                    editWindow.close();
                });

                Button cancelEditingButton = new Button("Anuluj", cancelEvent -> {
                    editWindow.close();
                });

                editForm.add(clientName, clientSurname, clientAdressStreet, clientAdressCity, clientAdressZipCode,
                        clientPhoneNumber, clientEmail, isClientACompany, clientNIPNumber);

                FormLayout buttonLayout = new FormLayout(saveButton, cancelEditingButton);

                editWindow.add(editForm, horizontalLayout, buttonLayout);
                editWindow.open();
            });

        });

        contextMenu.addItem("Dodaj samochód", event -> {
            event.getItem().ifPresent(owner -> {

                Dialog dialog = new Dialog();
                ComboBox<Car> carComboBox = new ComboBox<>();
                dialog.add(carComboBox);
                List<Car> cars = new ArrayList<>(carRepository.findAll());
                carComboBox.setLabel("Wybierz samochód");
                carComboBox.setItemLabelGenerator(Car::getPlate);
                carComboBox.setItems(cars.stream().filter(car1 ->
                        car1.getOwner() == null)
                        .collect(Collectors.toList()));

                Button save = new Button("Wybierz", eventSave -> {
                    // Jeśli nie działa to carService zamienić na carRepository
                    owner.getCars().add(carRepository.findByPlate(carComboBox.getValue().toString()));
                    ownerRepository.save(owner);

                    cars.forEach(car -> {
                                if (car.getPlate() == carComboBox.getValue().getPlate()) {
                                    car.setOwner(owner);
                                    carRepository.save(car);
                                }
                            }
                    );

                    dialog.removeAll();
                    Label label = new Label("Samochód o nr rej.: " + carComboBox.getValue().getPlate() +
                            " został dodany do " + owner.getOwnerName() + " " + owner.getOwnerSurname());
                    dialog.add(label);
                });

                dialog.add(save);
                dialog.open();
            });
        });

        contextMenu.addItem("Usuń", event -> {
            event.getItem().ifPresent(owner -> {
                ownerRepository.delete(owner);
                grid.setItems(ownerRepository.findAll());
            });
            System.out.println("Owner was removed");
        });

        grid.removeColumnByKey("cars");
        grid.removeColumnByKey("id");
        grid.removeColumnByKey("ownerName");
        grid.removeColumnByKey("ownerSurname");
        grid.removeColumnByKey("ownerAdressStreet");
        grid.removeColumnByKey("ownerAdressCity");
        grid.removeColumnByKey("ownerAdressZipCode");
        grid.removeColumnByKey("ownerPhoneNumber");
        grid.removeColumnByKey("ownerEmail");
        grid.removeColumnByKey("ownerNIPNumber");
        grid.removeColumnByKey("ownerACompany");
        grid.setColumnReorderingAllowed(true);

        //TODO Nie działa do końca tak jak powinno
        TextField searchField = new TextField();
        searchField.setPlaceholder("Znajdź");
        searchField.addValueChangeListener(event -> ownerRepository.findAll().stream().filter(owner ->
                StringUtils.containsIgnoreCase(owner.getOwnerName(), searchField.getValue()))
        );

        searchField.setValueChangeMode(ValueChangeMode.EAGER);


        add(searchField, grid);
    }

    //TODO add mouse item focus listener,
    //TODO add search field and method to searching
}
