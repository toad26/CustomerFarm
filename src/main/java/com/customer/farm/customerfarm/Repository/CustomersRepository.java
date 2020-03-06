package com.customer.farm.customerfarm.Repository;

import com.customer.farm.customerfarm.Entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomersRepository extends JpaRepository<Customers, Long> {
}
