package com.manager.repairshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.manager.repairshop.entity.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

    // fined by vehicle number
    // @Query("SELECT v FROM Vehicle v WHERE v.vehicle_number = 'KN-4909'")
    @Query(value = ("select * from `repairshop`.`vehicles` where `vehicle_number` = :vehicleNumber"), nativeQuery = true)
    public Vehicle getVehicleByVIN(String vehicleNumber);

    // fined by vehicle number
    @Query("SELECT v FROM Vehicle v WHERE v.vehicleNumber =:vehicleNumber ")
    public List<Vehicle> getVehicleByVehicleNumber(String vehicleNumber);

    @Query(value = ("DELETE FROM `repairshop`.`vehicles` WHERE (`vehicle_number` = :vehicleNumber)"), nativeQuery = true)
    public Vehicle deleteByVehicleNumber(String vehicleNumber);

}
