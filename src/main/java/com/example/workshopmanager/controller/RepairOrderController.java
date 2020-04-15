package com.example.workshopmanager.controller;

import com.example.workshopmanager.model.RepairOrder;
import com.example.workshopmanager.repository.RepairOrderRepository;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class RepairOrderController {

    private RepairOrderRepository repairOrderRepository;
    private LocalDateTime localDateTime;
    private RepairOrder repairOrder;

    public RepairOrderController(RepairOrderRepository repairOrderRepository) {
        this.repairOrderRepository = repairOrderRepository;
    }

    public String orderNumberGenerator() {
        String temp = "";
        localDateTime = LocalDateTime.now();
        int currentMonthValue = localDateTime.getMonthValue();
        int currentYearValue = localDateTime.getYear();
        if (repairOrderRepository.findLastOrderNumber() == null) {
            temp = "0/0/0";
        } else {
            temp = repairOrderRepository.findLastOrderNumber();
        }
        String[] splitedTemp = temp.split("/");
        int tempCounter = Integer.parseInt(splitedTemp[0]);
        int tempMonth = Integer.parseInt(splitedTemp[1]);
        int tempYear = Integer.parseInt(splitedTemp[2]);
        //TODO zerowanie licznika kazdego nowego miesiaca- nie działa!!!!
        if (tempCounter != 1 && (currentMonthValue > tempMonth || currentYearValue > tempYear)) {
            tempCounter = 1;
            return tempCounter + "/" + currentMonthValue + "/" + currentYearValue;
        } else {
            tempCounter++;
            return tempCounter + "/" + localDateTime.getMonthValue() + "/" + localDateTime.getYear();
        }
    }
}
