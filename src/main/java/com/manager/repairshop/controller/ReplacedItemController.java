package com.manager.repairshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.manager.repairshop.entity.ReplacedItem;
import com.manager.repairshop.service.ReplacedItemService;

@Controller
public class ReplacedItemController {

    @Autowired
    ReplacedItemService replacedItemService;

    @GetMapping("/addNewReplacedItem/{id}")
    public String getNewReplacedItemView(@RequestParam("id") String id, Model model) {

        // List<ReplacedItem> newItem = new ArrayList<ReplacedItem>();
        ReplacedItem item = new ReplacedItem();

        item.setJobId(id);

        model.addAttribute("item", item);

        return "addNewReplacedItem";
    }

    @PostMapping(value = "/addNewReplacedItem")
    public String addNewReplacedItem(@RequestParam("jobId") String jobId,
            @RequestParam("itemName") String itemName, @RequestParam("itemPrice") String itemPrice,
            ReplacedItem replacedItem) {

        replacedItem.setJobId(jobId);
        replacedItem.setItemName(itemName);
        replacedItem.setItemPrice(itemPrice);

        replacedItemService.saveItem(replacedItem);

        return "redirect:/updateJob/" + jobId;
    }

}
