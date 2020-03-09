package com.customer.farm.customerfarm.Repository;

import com.customer.farm.customerfarm.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    public Optional<Users> findByUsername(String username);
    public Users findUserByUsername(String username);
    public Users findUserByUsernameAndPassword(String username, String password);
}
