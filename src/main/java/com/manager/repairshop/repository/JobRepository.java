package com.manager.repairshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.manager.repairshop.entity.Jobs;

@Repository
public interface JobRepository extends JpaRepository<Jobs, Integer> {
    @Query("select j from Jobs j where j.status = 'pending'")
    public List<Jobs> getPendingJobs();

    @Query("select j from Jobs j where j.status = 'done'")
    public List<Jobs> getDoneJobs();

}
