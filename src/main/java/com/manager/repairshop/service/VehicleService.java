package com.manager.repairshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manager.repairshop.entity.Vehicle;
import com.manager.repairshop.repository.VehicleRepository;

@Service
public class VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicleByVIN(String vehicleNumber) {
        return vehicleRepository.getVehicleByVIN(vehicleNumber);
    }

    public List<Vehicle> getVehicleByVehicleNumber(String vehicleNumber) {
        return vehicleRepository.getVehicleByVehicleNumber(vehicleNumber);
    }

    public Vehicle save(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public void delete(String vehicleNumber) {
        vehicleRepository.deleteByVehicleNumber(vehicleNumber);
    }

}
