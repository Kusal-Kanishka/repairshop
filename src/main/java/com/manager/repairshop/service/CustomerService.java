package com.manager.repairshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manager.repairshop.entity.Customer;
import com.manager.repairshop.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer save(Customer employee) {
        return customerRepository.save(employee);
    }

    public Optional<Customer> getCustomerById(Integer employeeId) {
        return customerRepository.findById(employeeId);
    }

    public void delete(Integer id) {
        try {
            customerRepository.deleteById(id);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }
    }

}
