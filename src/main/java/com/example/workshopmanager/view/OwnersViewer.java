package com.example.workshopmanager.view;

import com.example.workshopmanager.controller.OwnerAdder;
import com.example.workshopmanager.model.Owner;
import com.example.workshopmanager.repository.OwnerRepository;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridContextMenu;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("allowners")
public class OwnersViewer extends VerticalLayout {

    private OwnerAdder ownerAdder;

    @Autowired
    public OwnersViewer(OwnerRepository ownerRepository){
        Grid<Owner> grid = new Grid<>(Owner.class);
        grid.getColumnByKey("id").setWidth("30px");
        grid.addColumn(Owner::getOwnerName).setHeader("Imię");
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
            System.out.println("Editing owner");
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
        add(grid);
    }

    //TODO add mouse item focus listener,
    //TODO implements edit method in context menu
    //TODO add search field and method to searching
}
