package com.manager.repairshop.entity;

public class JobDetail {

    private Integer jobId;
    private String createdDate;
    private String vehicleNumber;
    private String vehicleModel;
    private String customerName;
    private String customerMobile;
    private String status;

    public JobDetail(Integer jobId, String createdDate, String vehicleNumber, String vehicleModel, String customerName,
            String customerMobile, String status) {
        this.jobId = jobId;
        this.createdDate = createdDate;
        this.vehicleNumber = vehicleNumber;
        this.vehicleModel = vehicleModel;
        this.customerName = customerName;
        this.customerMobile = customerMobile;
        this.status = status;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
