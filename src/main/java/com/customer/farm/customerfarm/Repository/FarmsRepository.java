package com.customer.farm.customerfarm.Repository;

import com.customer.farm.customerfarm.Entity.Farms;
import com.customer.farm.customerfarm.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FarmsRepository extends JpaRepository<Farms, Long> {
    public List<Farms> findFarmsByCustomersId(Long customerId);
    public List<Farms> findFarmsByUsersId(Long userId);
}
