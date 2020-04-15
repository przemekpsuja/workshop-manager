package com.example.workshopmanager.view;

import com.example.workshopmanager.model.Invoice;
import com.example.workshopmanager.repository.CarRepository;
import com.example.workshopmanager.repository.InvoiceRepository;
import com.example.workshopmanager.repository.OwnerRepository;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("invoices-view")
public class InvoicesView extends VerticalLayout {

    private Grid<Invoice> invoicesGrid;
    private TextField searchField;

    public InvoicesView(InvoiceRepository invoiceRepository, CarRepository carRepository, OwnerRepository ownerRepository) {
        this.invoicesGrid = new Grid<>();
        this.searchField = new TextField();
        searchField.setPlaceholder("Szukaj...");
        searchField.setClearButtonVisible(true);
        invoicesGrid.setItems(invoiceRepository.findAll());

        add(searchField, invoicesGrid);
    }
}
