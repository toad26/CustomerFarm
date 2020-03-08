package com.customer.farm.customerfarm.Controller;

import com.customer.farm.customerfarm.Entity.Customers;
import com.customer.farm.customerfarm.Entity.Farms;
import com.customer.farm.customerfarm.Entity.Users;
import com.customer.farm.customerfarm.Repository.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CustomersController {

    @Autowired
    CustomersRepository customersRepository;

    @GetMapping("/customers")
    public ResponseEntity< List<Customers>> getAllUsers(){
         List<Customers> entities = customersRepository.findAll();
        return ResponseEntity.ok().body(entities);
    }
    @GetMapping("/customers/{id}")
    public ResponseEntity<Customers> getCustomerById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {

        Customers customer = customersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + id));
        return ResponseEntity.ok().body(customer);
    }
    @PostMapping("/customers")
    public Customers createCustomer(@Valid @RequestBody Customers customer){
        return customersRepository.save(customer);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<Customers> updateCustomer(@PathVariable(value = "id") Long customerId,
                                            @Valid @RequestBody Customers customers){

        Customers customer = customersRepository.getOne(customerId);
        customer.setName(customer.getName());
        customer.setAddress(customer.getAddress());
        customer.setEmail(customer.getEmail());
        customer.setFarms(customer.getFarms());
        customer.setUsers(customer.getUsers());
        final Customers finalCustomer = customersRepository.save(customer);
        return ResponseEntity.ok(finalCustomer);
    }

    @DeleteMapping("customers/{id}")
    public Map<String, Boolean> deleteCustomer(@PathVariable(value = "id") Long customerId) throws ResourceNotFoundException {
        Customers customer = customersRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));
        customersRepository.delete(customer);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }



}
