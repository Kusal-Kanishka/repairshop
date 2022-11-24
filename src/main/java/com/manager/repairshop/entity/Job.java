package com.manager.repairshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String vehicle_number;
    @Column
    private String date;
    @Column
    private String customer_story;
    @Column
    private String status;
    @Column
    private Integer employeeId;

    public Job(Integer id, String vehicle_number, String date, String customer_story, String status,
            Integer employeeId) {
        this.id = id;
        this.vehicle_number = vehicle_number;
        this.date = date;
        this.customer_story = customer_story;
        this.status = status;
        this.employeeId = employeeId;
    }

    public Job() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVehicle_number() {
        return vehicle_number;
    }

    public void setVehicle_number(String vehicle_number) {
        this.vehicle_number = vehicle_number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomer_story() {
        return customer_story;
    }

    public void setCustomer_story(String customer_story) {
        this.customer_story = customer_story;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }
}
