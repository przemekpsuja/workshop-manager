package com.example.workshopmanager.view;

import com.example.workshopmanager.model.RepairOrder;
import com.example.workshopmanager.repository.RepairOrderRepository;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route("all-orders")
public class OrdersGridView extends VerticalLayout {

    private RepairOrderRepository repairOrderRepository;
    private Grid<RepairOrder> ordersGrid = new Grid<>();

    public OrdersGridView(RepairOrderRepository repairOrderRepository, RepairOrder repairOrder) {
        TextField search = new TextField();
        search.setPlaceholder("Szukaj...");
        search.setClearButtonVisible(true);
        search.setValueChangeMode(ValueChangeMode.EAGER);
        search.addValueChangeListener(event -> {
        //TODO implement searching method

        });
        ordersGrid.addColumn(RepairOrder::getOrderNumber).setHeader("Nr zlecenia");
        ordersGrid.addColumn(RepairOrder::getStatus).setHeader("Status");
        ordersGrid.addColumn(RepairOrder::getDescription).setHeader("Opis");
        ordersGrid.addColumn(RepairOrder::getCarPlate).setHeader("Nr rej.");
        ordersGrid.addColumn(RepairOrder::getCarBrand).setHeader("Marka");
        ordersGrid.addColumn(RepairOrder::getCarModel).setHeader("Model");
        ordersGrid.addColumn(RepairOrder::getOwnerFullname).setHeader("Zlecający");
        ordersGrid.setItems(repairOrderRepository.findAll());
        ordersGrid.setMultiSort(true);
        ordersGrid.addThemeVariants(GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);
        ordersGrid.addContextMenu();
        add(search, ordersGrid);
    }
}
