package com.manager.repairshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.manager.repairshop.entity.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {
    @Query("select j from Job j where j.status = 'pending'")
    public List<Job> getPendingJobs();

    @Query("select j from Job j where j.status = 'done'")
    public List<Job> getDoneJobs();

}
