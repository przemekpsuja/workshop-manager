package com.example.workshopmanager.view;

import com.example.workshopmanager.controller.OwnerAdder;
import com.example.workshopmanager.model.Owner;
import com.example.workshopmanager.repository.OwnerRepository;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("allowners")
public class OwnersViewer extends VerticalLayout {

    private OwnerAdder ownerAdder;

    @Autowired
    public OwnersViewer(OwnerRepository ownerRepository){
        Grid<Owner> grid = new Grid<>(Owner.class);
        grid.removeColumnByKey("cars");
        grid.setItems(ownerRepository.findAll());
        add(grid);
    }


}
