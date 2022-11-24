package com.manager.repairshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manager.repairshop.repository.EmployeeRepository;
import com.manager.repairshop.entity.Employee;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Optional<Employee> getEmployeeById(Integer employeeId) {
        return employeeRepository.findById(employeeId);
    }

    public void delete(Integer id) {
        try {
            employeeRepository.deleteById(id);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }
    }
}
