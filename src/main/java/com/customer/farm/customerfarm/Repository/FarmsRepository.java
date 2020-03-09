package com.customer.farm.customerfarm.Repository;

import com.customer.farm.customerfarm.Entity.Farms;
import com.customer.farm.customerfarm.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FarmsRepository extends JpaRepository<Farms, Long> {
    public List<Farms> findFarmsByCustomersId(@Param("customerId") Long customerId);
    public List<Farms> findFarmsByUsersId(Long userId);
}
