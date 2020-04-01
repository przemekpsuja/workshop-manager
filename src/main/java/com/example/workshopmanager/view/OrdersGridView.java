package com.example.workshopmanager.view;

import com.example.workshopmanager.model.StatusOrder;
import com.example.workshopmanager.repository.RepairOrderRepository;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("allOrders")
public class OrdersGridView extends VerticalLayout {

    private RepairOrderRepository repairOrderRepository;

    private Grid<StatusOrder> ordersGrid = new Grid<>(StatusOrder.class);

    public OrdersGridView(RepairOrderRepository repairOrderRepository) {
        ordersGrid.getColumnByKey("orderNumber");
//        ordersGrid.addColumn(RepairOrder::getCar).setHeader("Samochód");
//        ordersGrid.addColumn(RepairOrder::getOwner).setHeader("Zlecający");
//        ordersGrid.addColumn(RepairOrder::getStatus).setHeader("Samochód");
//        ordersGrid.addColumn(RepairOrder::getCar).setHeader("Samochód");
//        ordersGrid.addColumn(RepairOrder::getCar).setHeader("Samochód");

        add(ordersGrid);
    }
}
