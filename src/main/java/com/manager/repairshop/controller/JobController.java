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

import com.manager.repairshop.entity.Jobs;
import com.manager.repairshop.entity.ReplacedItem;

import com.manager.repairshop.repository.JobRepository;
import com.manager.repairshop.service.JobService;

@Controller
public class JobController {

    @Autowired
    JobService jobService;

    @Autowired
    JobRepository jobRepository;

    @Autowired
    ReplacedItemService replacedItemService;

    @GetMapping("/new-job")
    public String viewNewJobPage(Model model) {

        Jobs jobs = new Jobs();
        model.addAttribute("job", jobs);
        return "newJob";
    }

    @PostMapping("/addNewJob")
    public String addNewJob(Jobs jobs,
            @RequestParam("vehicle_number") String vehicleNumber,
            @RequestParam("vehicle_model") String vehicleModel,
            @RequestParam("date") String date,
            @RequestParam("customer_name") String customerName,
            @RequestParam("mobile_number") String mobileNumber,
            @RequestParam("customer_story") String customerStory) {

        if (date.isEmpty()) {
            date = java.time.LocalDate.now().toString();
        }

        jobs.setVehicle_number(vehicleNumber);
        jobs.setVehicle_model(vehicleModel);
        jobs.setDate(date);
        jobs.setCustomer_name(customerName);
        jobs.setMobile_number(mobileNumber);
        jobs.setCustomer_story(customerStory);
        jobs.setStatus("pending");

        jobService.saveJob(jobs);

        return "redirect:/home";
    }

    @GetMapping("/updateJob/{id}")
    public String viewEditJob(Model model, @PathVariable(name = "id") Integer id) {

        Optional<Jobs> jobById = jobService.getJobById(id);
        model.addAttribute("job", jobById);

        List<ReplacedItem> replacedItems = replacedItemService.getAll();

        List<ReplacedItem> replacedItemsForJob = new ArrayList<>();

        for (ReplacedItem replacedItem : replacedItems) {
            if (replacedItem.getJobId().equals(id.toString())) {

                replacedItemsForJob.add(replacedItem);
            }
            System.out.println(replacedItem.getId());

        }
        model.addAttribute("jobId", id);
        model.addAttribute("replacedItems", replacedItemsForJob);

        return "jobEdit";
    }

    @PostMapping("/updateJob")
    public String updateJob(Jobs jobs,
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
        jobs.setVehicle_model(vehicleModel);
        jobs.setDate(date);
        jobs.setCustomer_name(customerName);
        jobs.setMobile_number(mobileNumber);
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
        return "redirect:/pending-jobs";
    }

    @RequestMapping(value = "/deleteCompletedJob", method = { RequestMethod.DELETE, RequestMethod.GET })
    public String deleteCompletedJobById(Integer id) {
        jobService.delete(id);
        return "redirect:/compleat-jobs";
    }

    @GetMapping("/pending-jobs")
    public String viewPendingJobsPage(Model model) {
        List<Jobs> pendingJobList = jobService.getPendingJobs();
        model.addAttribute("pendingJobList", pendingJobList);
        return "pendingJobs";
    }

    @GetMapping("/compleat-jobs")
    public String viewCompleatJobsPage(Model model) {
        List<Jobs> compleatJobList = jobService.getCompleatJobs();
        model.addAttribute("compleatJobList", compleatJobList);
        return "compleatJobs";
    }

}
