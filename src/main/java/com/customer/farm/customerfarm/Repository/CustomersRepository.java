package com.customer.farm.customerfarm.Repository;

import com.customer.farm.customerfarm.Entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersRepository extends JpaRepository<Customers, Long> {
    public Customers getById(Long customersId);
}
