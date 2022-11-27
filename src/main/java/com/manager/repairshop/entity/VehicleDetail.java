package com.manager.repairshop.entity;

public class VehicleDetail {

    private String vehicleNumber;
    private String brand;
    private String model;
    private String customerName;
    private String createdDate;

    public VehicleDetail(String vehicleNumber, String brand, String model, String customerName, String createdDate) {
        this.vehicleNumber = vehicleNumber;
        this.brand = brand;
        this.model = model;
        this.customerName = customerName;
        this.createdDate = createdDate;
    }

    public VehicleDetail() {
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

}
