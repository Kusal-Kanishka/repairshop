package com.manager.repairshop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.manager.repairshop.entity.Customer;
import com.manager.repairshop.service.CustomerService;

@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/customers")
    public String employeesList(Model model) {

        Customer customer = new Customer();
        model.addAttribute("customer", customer);

        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);

        return "customers/customersData";
    }

    @PostMapping("/addNewCustomer")
    public String addNewJob(Customer customer,
            @RequestParam("customerName") String customerName,
            @RequestParam("contactNumber") String contactNumber, @RequestParam("nic") String nic) {

        String date = java.time.LocalDate.now().toString();

        customer.setCustomerName(customerName);
        customer.setContactNumber(contactNumber);
        customer.setNic(nic);
        customer.setCreatedDate(date);

        customerService.save(customer);

        return "redirect:/customers";
    }

    @GetMapping("/customer/{id}/update")
    public String getCustomerUpdateView(@PathVariable(name = "id") Integer id, Model model) {

        Optional<Customer> customer = customerService.getCustomerById(id);
        model.addAttribute("customer", customer);

        return "customers/customerUpdate";
    }

    @PostMapping("/customer/saveUpdate")
    public String updateCustomer(Customer customer,
            @RequestParam("customerName") String customerName,
            @RequestParam("contactNumber") String contactNumber, @RequestParam("nic") String nic) {

        String date = java.time.LocalDate.now().toString();

        customer.setCustomerName(customerName);
        customer.setContactNumber(contactNumber);
        customer.setNic(nic);
        customer.setCreatedDate(date);

        customerService.save(customer);

        return "redirect:/customers";
    }

    // /deletePendingJob/(id=${customer.id})
    @RequestMapping(value = "/customer/{id}/delete", method = { RequestMethod.DELETE, RequestMethod.GET })
    public String deleteEmployee(@PathVariable(name = "id") Integer id) {
        customerService.delete(id);
        return "redirect:/customers";
    }

}
