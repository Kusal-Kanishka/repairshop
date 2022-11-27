package com.manager.repairshop.controller;

import java.util.Date;
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

import com.manager.repairshop.entity.Employee;
import com.manager.repairshop.service.EmployeeService;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employees")
    public String employeesList(Model model) {

        Employee employee = new Employee();
        model.addAttribute("employee", employee);

        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);

        return "employee/employeeData";
    }

    @PostMapping("/employee/save")
    public String saveNewEmployee(Employee employee, @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName, @RequestParam("nic") String nic,
            @RequestParam("mobileNumber") String mobileNumber) {

        Date currentDate = new Date();

        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setNic(nic);
        employee.setMobileNumber(mobileNumber);
        employee.setCreated_date(currentDate);

        employeeService.save(employee);

        return "redirect:/employees";
    }

    @GetMapping("/employee/{id}/update")
    public String getEmployeeById(@PathVariable(name = "id") Integer id, Model model) {

        Optional<Employee> employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);

        return "employee/employeeUpdate";
    }

    @PostMapping("/employee/update")
    public String updateEmployee(Employee employee, @RequestParam("id") Integer id,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName, @RequestParam("nic") String nic,
            @RequestParam("mobileNumber") String mobileNumber) {

        Date currentDate = new Date();

        employee.setId(id);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setNic(nic);
        employee.setMobileNumber(mobileNumber);
        employee.setCreated_date(currentDate);

        employeeService.save(employee);

        return "redirect:/employees";
    }

    @RequestMapping(value = "/employee/{id}/delete", method = { RequestMethod.DELETE, RequestMethod.GET })
    public String deleteEmployee(@PathVariable(name = "id") Integer id) {
        employeeService.delete(id);
        return "redirect:/employees";
    }

}
