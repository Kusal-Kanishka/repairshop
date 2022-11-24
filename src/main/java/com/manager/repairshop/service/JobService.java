package com.manager.repairshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manager.repairshop.entity.Job;
import com.manager.repairshop.repository.JobRepository;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public List<Job> getAll() {
        return jobRepository.findAll();
    }

    public List<Job> getPendingJobs() {
        return jobRepository.getPendingJobs();
    }

    public List<Job> getCompleatJobs() {
        return jobRepository.getDoneJobs();
    }

    public Optional<Job> getJobById(Integer id) {
        return jobRepository.findById(id);
    }

    public void delete(Integer id) {
        jobRepository.deleteById(id);
    }

    public Job saveJob(Job job) {

        return jobRepository.save(job);
    }
}
