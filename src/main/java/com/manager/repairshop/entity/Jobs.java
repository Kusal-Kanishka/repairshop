package com.manager.repairshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "jobs")
public class Jobs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String vehicle_number;

    @Column
    private String vehicle_model;

    @Column
    private String date;

    @Column
    private String mobile_number;

    @Column
    private String customer_name;

    @Column
    private String customer_story;

    @Column
    private String status;

    public Jobs(Integer id, String vehicle_number, String vehicle_model, String date, String mobile_number,
            String customer_name, String customer_story, String status) {
        this.id = id;
        this.vehicle_number = vehicle_number;
        this.vehicle_model = vehicle_model;
        this.date = date;
        this.mobile_number = mobile_number;
        this.customer_name = customer_name;
        this.customer_story = customer_story;
        this.status = status;
    }

    public Jobs() {
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

    public String getVehicle_model() {
        return vehicle_model;
    }

    public void setVehicle_model(String vehicle_model) {
        this.vehicle_model = vehicle_model;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
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
}
