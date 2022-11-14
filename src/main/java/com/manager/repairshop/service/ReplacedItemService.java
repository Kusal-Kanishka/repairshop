package com.manager.repairshop.service;

import com.manager.repairshop.entity.ReplacedItem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manager.repairshop.repository.ReplacedItemRepository;

@Service
public class ReplacedItemService {

    @Autowired
    private ReplacedItemRepository replacedItemRepository;

    public List<ReplacedItem> getAll() {
        return replacedItemRepository.findAll();
    }

    public ReplacedItem saveItem(ReplacedItem replacedItem) {
        return replacedItemRepository.save(replacedItem);
    }

}
