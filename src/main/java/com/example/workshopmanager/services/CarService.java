package com.example.workshopmanager.services;

import com.example.workshopmanager.model.Car;
import com.example.workshopmanager.repository.CarRepository;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car findByPlate(final String plate) {
        return carRepository.findById(plate)
                .orElseThrow(() -> new RuntimeException("Car with this plate doesn't exist"));
    }
}
