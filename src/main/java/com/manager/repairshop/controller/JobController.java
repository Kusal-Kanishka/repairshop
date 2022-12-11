package com.manager.repairshop.controller;

import com.manager.repairshop.service.ReplacedItemService;

import java.util.ArrayList;
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
import com.manager.repairshop.entity.Employee;
import com.manager.repairshop.entity.Job;
import com.manager.repairshop.entity.ReplacedItem;
import com.manager.repairshop.entity.Vehicle;
import com.manager.repairshop.repository.JobRepository;
import com.manager.repairshop.repository.VehicleRepository;
import com.manager.repairshop.service.CustomerService;
import com.manager.repairshop.service.EmployeeService;
import com.manager.repairshop.service.JobService;

@Controller
public class JobController {

    @Autowired
    JobService jobService;

    @Autowired
    JobRepository jobRepository;

    @Autowired
    ReplacedItemService replacedItemService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    CustomerService customerService;

    @Autowired
    VehicleRepository vehicleRepository;

    @GetMapping("/new-job")
    public String viewNewJobPage(Model model) {

        Job jobs = new Job();
        model.addAttribute("job", jobs);
        return "newJob";
    }

    @PostMapping("/addNewJob")
    public String addNewJob(Job job,
            @RequestParam("vehicle_number") String vehicleNumber,
            @RequestParam("date") String date, @RequestParam("employeeId") Integer employeeId,
            @RequestParam("customer_story") String customerStory) {

        if (date.isEmpty()) {
            date = java.time.LocalDate.now().toString();
        }

        System.out.println(employeeId);

        job.setVehicle_number(vehicleNumber);
        job.setDate(date);
        job.setCustomer_story(customerStory);
        job.setStatus("pending");
        job.setEmployeeId(employeeId);

        jobService.saveJob(job);

        return "redirect:/home";
    }

    @GetMapping("/viewJob/{id}")
    public String viewEditJob(Model model, @PathVariable(name = "id") Integer id) {

        model.addAttribute("jobId", id);

        Optional<Job> jobById = jobService.getJobById(id);
        model.addAttribute("job", jobById);

        Optional<Employee> employee = employeeService.getEmployeeById(jobById.get().getEmployeeId());
        String employeeName = employee.get().getFirstName() + " " + employee.get().getLastName();
        model.addAttribute("employeeName", employeeName);

        List<ReplacedItem> ReplacedItemsList = replacedItemService.getReplacedItemsById(id.toString());

        List<Vehicle> vData = vehicleRepository.getVehicleByVehicleNumber(jobById.get().getVehicle_number());

        for (Vehicle vehicle : vData) {
            String vBrand = vehicle.getBrand();
            model.addAttribute("vBrand", vBrand);
            String vModel = vehicle.getModel();
            model.addAttribute("vModel", vModel);
            Optional<Customer> customer = customerService.getCustomerById(vehicle.getCustomerId());
            String customerName = customer.get().getCustomerName();
            model.addAttribute("customerName", customerName);
            String mobileNumber = customer.get().getContactNumber();
            model.addAttribute("mobileNumber", mobileNumber);
        }

        model.addAttribute("replacedItems", ReplacedItemsList);

        return "job/jobView";
    }

    @PostMapping("/updateJob")
    public String updateJob(Job jobs,
            @RequestParam("id") Integer id,
            @RequestParam("vehicle_number") String vehicleNumber,
            @RequestParam("vehicle_model") String vehicleModel,
            @RequestParam("date") String date,
            @RequestParam("customer_name") String customerName,
            @RequestParam("mobile_number") String mobileNumber,
            @RequestParam("customer_story") String customerStory,
            @RequestParam("status") String status) {

        if (date.isEmpty()) {
            date = java.time.LocalDate.now().toString();
        }

        jobs.setId(id);
        jobs.setVehicle_number(vehicleNumber);
        jobs.setDate(date);
        jobs.setCustomer_story(customerStory);
        jobs.setStatus(status);

        jobService.saveJob(jobs);

        return "redirect:/updateJob/" + id;
    }

    // @GetMapping("/deleteJob/{id}")
    // public String deleteJobById(@PathVariable("id") Integer id) {
    // jobService.delete(id);
    // return "redirect:/pending-jobs";
    // }

    @RequestMapping(value = "/deletePendingJob", method = { RequestMethod.DELETE, RequestMethod.GET })
    public String deletePendingJobById(Integer id) {
        jobService.delete(id);
        return "redirect:/jobs";
    }

    @RequestMapping(value = "/deleteCompletedJob", method = { RequestMethod.DELETE, RequestMethod.GET })
    public String deleteCompletedJobById(Integer id) {
        jobService.delete(id);
        return "redirect:/jobs";
    }

    @GetMapping("/jobs")
    public String viewCompleatJobsPage(Model model) {

        List<Job> pendingJobList = jobService.getPendingJobs();
        model.addAttribute("pendingJobList", pendingJobList);

        List<Job> compleatJobList = jobService.getCompleatJobs();
        model.addAttribute("compleatJobList", compleatJobList);

        return "job/jobsData";
    }

}
