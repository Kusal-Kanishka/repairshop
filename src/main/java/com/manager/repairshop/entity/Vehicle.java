package com.manager.repairshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @Column
    private String vehicleNumber;
    @Column
    private String brand;
    @Column
    private String model;
    @Column
    private Integer customerId;
    @Column
    private String createdDate;

    public Vehicle(String vehicleNumber, String brand, String model, Integer customerId, String createdDate) {
        this.vehicleNumber = vehicleNumber;
        this.brand = brand;
        this.model = model;
        this.customerId = customerId;
        this.createdDate = createdDate;
    }

    public Vehicle() {
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
