package com.customer.farm.customerfarm.Repository;

import com.customer.farm.customerfarm.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
    public Users findUserByUsername(String username);
    public Users findUserByUsernameAndPassword(String username, String password);
}
