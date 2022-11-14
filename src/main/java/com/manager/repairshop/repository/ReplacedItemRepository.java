package com.manager.repairshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manager.repairshop.entity.ReplacedItem;

@Repository
public interface ReplacedItemRepository extends JpaRepository<ReplacedItem, Integer> {

}
