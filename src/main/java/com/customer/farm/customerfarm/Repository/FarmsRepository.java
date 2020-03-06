package com.customer.farm.customerfarm.Repository;

import com.customer.farm.customerfarm.Entity.Farms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmsRepository extends JpaRepository<Farms, Long> {
}
