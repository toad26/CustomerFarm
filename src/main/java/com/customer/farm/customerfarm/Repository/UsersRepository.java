package com.customer.farm.customerfarm.Repository;

import com.customer.farm.customerfarm.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    public Optional<Users> findUserByUsername(String username);
    public Users findUserByUsernameAndPassword(String username, String password);
}
