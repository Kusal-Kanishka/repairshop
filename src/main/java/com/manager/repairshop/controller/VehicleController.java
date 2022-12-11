package com.manager.repairshop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.manager.repairshop.entity.Customer;
import com.manager.repairshop.entity.Vehicle;
import com.manager.repairshop.entity.VehicleDetail;
import com.manager.repairshop.repository.VehicleRepository;
import com.manager.repairshop.service.CustomerService;
import com.manager.repairshop.service.VehicleService;

@Controller
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @Autowired
    CustomerService customerService;

    @Autowired
    VehicleRepository vehicleRepository;

    @GetMapping("/vehicles")
    public String getVehicleList(Model model) {

        // Vehicle vehicle = new Vehicle();
        // model.addAttribute("null", vehicle);

        List<VehicleDetail> vehicleDetailList = new ArrayList<>();

        List<Vehicle> vehiclesList = vehicleService.findAll();
        // model.addAttribute("vehicles", vehicles);

        for (Vehicle vehicle : vehiclesList) {

            VehicleDetail vehicleDetail = new VehicleDetail();

            vehicleDetail.setVehicleNumber(vehicle.getVehicleNumber());
            vehicleDetail.setBrand(vehicle.getBrand());
            vehicleDetail.setModel(vehicle.getModel());
            Optional<Customer> customerData = customerService.getCustomerById(vehicle.getCustomerId());
            if (!customerData.isEmpty()) {
                String customerName = customerData.get().getCustomerName();
                vehicleDetail.setCustomerName(customerName);
            } else {
                vehicleDetail.setCustomerName("Customer Not Available");
            }

            vehicleDetailList.add(vehicleDetail);

        }

        model.addAttribute("vehiclesList", vehicleDetailList);

        return "vehicles/vehicleData";
    }

    @PostMapping("/addNewVehicle/save")
    public String addNewVehicle(Vehicle vehicle, @RequestParam("vehicleNumber") String vehicleNumber,
            @RequestParam("brand") String brand,
            @RequestParam("model") String model,
            @RequestParam("customerId") Integer customerId) {

        String date = java.time.LocalDate.now().toString();

        vehicle.setVehicleNumber(vehicleNumber);
        vehicle.setBrand(brand);
        vehicle.setModel(model);
        vehicle.setCustomerId(customerId);
        vehicle.setCreatedDate(date);

        vehicleService.save(vehicle);

        return "redirect:/vehicles";
    }

    @GetMapping("/vehicle/{vehicleNumber}/update")
    public String getCustomerUpdateView(@PathVariable(name = "vehicleNumber") String vehicleNumber, Model model) {

        System.out.println(vehicleNumber);

        Vehicle vehicle = vehicleService.getVehicleByVIN(vehicleNumber);
        model.addAttribute("vehicle", vehicle);

        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);

        // Optional<Customer> customer = customerService.getCustomerById(vehicleNumber);

        return "vehicles/vehicleUpdate";
    }

}
