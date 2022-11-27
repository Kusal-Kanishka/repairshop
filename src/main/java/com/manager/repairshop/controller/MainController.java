package com.manager.repairshop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.manager.repairshop.entity.Customer;
import com.manager.repairshop.entity.Employee;
import com.manager.repairshop.entity.Job;
import com.manager.repairshop.entity.JobDetail;
import com.manager.repairshop.entity.Vehicle;
import com.manager.repairshop.repository.VehicleRepository;
import com.manager.repairshop.service.CustomerService;
import com.manager.repairshop.service.EmployeeService;
import com.manager.repairshop.service.JobService;
import com.manager.repairshop.service.VehicleService;

@Controller
public class MainController {

    @Autowired
    JobService jobService;

    @Autowired
    VehicleService vehicleService;

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    CustomerService customerService;

    @GetMapping("/login")
    public String viewLoginPage() {
        return "login";
    }

    @GetMapping("")
    public String redirect() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String viewHomePage(HttpServletRequest httpServletRequest, Model model, JobDetail jobDetails,
            Vehicle vehicle) throws NoSuchElementException {

        // create new pending job list with full details
        List<JobDetail> pendinJobList = new ArrayList<>();

        // get pending job list
        List<Job> pendingJobs = jobService.getPendingJobs();

        // get employee list and Send employee list to frontend.
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);

        // get customer list and Send customer list to frontend.
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);

        Customer customer = new Customer();
        model.addAttribute("customer", customer);

        model.addAttribute("vehicle", vehicle);

        // add data into "pendingJobList" with other
        for (Job job : pendingJobs) {

            JobDetail jobData = new JobDetail();

            jobData.setJobId(job.getId());
            jobData.setCreatedDate(job.getDate());
            jobData.setVehicleNumber(job.getVehicle_number());
            jobData.setStatus(job.getStatus());

            List<Vehicle> vData = vehicleRepository.getVehicleByVehicleNumber(job.getVehicle_number());

            if (vData.isEmpty()) {
                // System.out.println("empty");
                jobData.setVehicleModel("Vehicle Not Available");
                jobData.setCustomerName("Customer Not Available");
                jobData.setCustomerMobile("Customer Not Available");
            } else {
                for (Vehicle vD : vData) {
                    jobData.setVehicleModel(vD.getModel());

                    Optional<Customer> customerData = customerService.getCustomerById(vD.getCustomerId());
                    if (!customerData.isEmpty()) {
                        String customerName = customerData.get().getCustomerName();
                        String contactNumber = customerData.get().getContactNumber();
                        jobData.setCustomerName(customerName);
                        jobData.setCustomerMobile(contactNumber);
                    } else {
                        jobData.setCustomerName("Customer Not Available");
                        jobData.setCustomerMobile("Customer Not Available");
                    }
                }
            }

            pendinJobList.add(jobData);

        }
        model.addAttribute("pendingJobList", pendinJobList);

        Job jobs = new Job();
        model.addAttribute("job", jobs);

        // Locale currLocale = httpServletRequest.getLocale();
        // String countryCode = currLocale.getCountry();
        // String countryName = currLocale.getDisplayCountry();

        // String langCode = currLocale.getLanguage();
        // String langName = currLocale.getDisplayLanguage();

        // System.out.println("countryCode " + countryCode);
        // System.out.println("countryName " + countryName);
        // System.out.println("langCode " + langCode);
        // System.out.println("langName " + langName);

        return "index";
    }

}