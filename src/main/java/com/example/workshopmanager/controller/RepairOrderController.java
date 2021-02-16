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
        int currentDayValue = localDateTime.getDayOfMonth();
//        int currentHourValue = localDateTime.getHour();
//        int currentMinuteValue = localDateTime.getMinute();
//        int currentSecondsValue = localDateTime.getSecond();
//        Instant instant = Instant.now();

        if (repairOrderRepository.findLastOrderNumber() == null) {
            temp = "0/0/0";
        } else {
            temp = repairOrderRepository.findLastOrderNumber();
        }

        String[] splitedTemp = temp.split("/");
        int tempCounterValue = Integer.parseInt(splitedTemp[0]);
        int tempMonthValue = Integer.parseInt(splitedTemp[1]);
        int tempYearValue = Integer.parseInt(splitedTemp[2]);

        //TODO zerowanie licznika kazdego nowego miesiaca- nie działa!!!!
        if (tempCounterValue >= 1 && ((currentMonthValue > tempMonthValue || currentYearValue > tempYearValue))
                && localDateTime.isAfter(repairOrderRepository.findLastOrderCreatedTime())) {
            tempCounterValue = 1;
            return tempCounterValue + "/" + currentMonthValue + "/" + currentYearValue;
        } else {
            return ++tempCounterValue + "/" + localDateTime.getMonthValue() + "/" + localDateTime.getYear();
        }
    }

}
