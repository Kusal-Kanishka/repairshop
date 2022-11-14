package com.manager.repairshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manager.repairshop.entity.Jobs;
import com.manager.repairshop.repository.JobRepository;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public List<Jobs> getAll() {
        return jobRepository.findAll();
    }

    public List<Jobs> getPendingJobs() {
        return jobRepository.getPendingJobs();
    }

    public List<Jobs> getCompleatJobs() {
        return jobRepository.getDoneJobs();
    }

    public Optional<Jobs> getJobById(Integer id) {
        return jobRepository.findById(id);
    }

    public void delete(Integer id) {
        jobRepository.deleteById(id);
    }

    public Jobs saveJob(Jobs jobs) {

        return jobRepository.save(jobs);
    }
}
