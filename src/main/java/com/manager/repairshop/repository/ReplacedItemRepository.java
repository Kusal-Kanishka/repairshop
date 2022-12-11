package com.manager.repairshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.manager.repairshop.entity.ReplacedItem;

@Repository
public interface ReplacedItemRepository extends JpaRepository<ReplacedItem, Integer> {

    @Query(value = ("select * from repairshop.replaced_item where job_id = :JobId"), nativeQuery = true)
    public List<ReplacedItem> getReplacedItemsByJobId(String JobId);

}
