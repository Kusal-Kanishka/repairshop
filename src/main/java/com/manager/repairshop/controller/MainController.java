package com.manager.repairshop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.manager.repairshop.entity.Employee;
import com.manager.repairshop.entity.Job;
import com.manager.repairshop.entity.JobDetail;
import com.manager.repairshop.service.EmployeeService;
import com.manager.repairshop.service.JobService;

@Controller
public class MainController {

    @Autowired
    JobService jobService;

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/login")
    public String viewLoginPage() {
        return "login";
    }

    @GetMapping("")
    public String redirect() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String viewHomePage(HttpServletRequest httpServletRequest, Model model, JobDetail jobDetails) {

        // create new pending job list with full details
        List<JobDetail> pendinJobList = new ArrayList<>();

        // get pending job list
        List<Job> pendingJobs = jobService.getPendingJobs();

        // get employee list
        List<Employee> employees = employeeService.findAll();
        // Send employee list to frontend
        model.addAttribute("employees", employees);

        // add data into "pendingJobList" with other
        for (Job job : pendingJobs) {

            jobDetails.setJobId(job.getId());
            jobDetails.setCreatedDate(job.getDate());
            jobDetails.setVehicleNumber(job.getVehicle_number());
            jobDetails.setVehicleModel(null);
            jobDetails.setCustomerName(null);
            jobDetails.setCustomerMobile(null);
            jobDetails.setStatus(null);
        }
        // model.addAttribute("pendingJobList", pendingJobList);

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