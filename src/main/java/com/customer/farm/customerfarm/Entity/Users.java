package com.customer.farm.customerfarm.Entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Users {
    private Integer id;
    private Integer rolesId;
    private Integer customersId;
    private String username;
    private String password;
    private String fullName;
    private Integer registrationNumber;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "roles_id", nullable = false)
    public Integer getRolesId() {
        return rolesId;
    }

    public void setRolesId(int rolesId) {
        this.rolesId = rolesId;
    }

    public void setRolesId(Integer rolesId) {
        this.rolesId = rolesId;
    }

    @Basic
    @Column(name = "customers_id", nullable = false)
    public Integer getCustomersId() {
        return customersId;
    }

    public void setCustomersId(int customersId) {
        this.customersId = customersId;
    }

    public void setCustomersId(Integer customersId) {
        this.customersId = customersId;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 150)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 150)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "full_name", nullable = true, length = 200)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Basic
    @Column(name = "registration_number", nullable = true)
    public Integer getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(Integer registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return Objects.equals(id, users.id) &&
                Objects.equals(rolesId, users.rolesId) &&
                Objects.equals(customersId, users.customersId) &&
                Objects.equals(username, users.username) &&
                Objects.equals(password, users.password) &&
                Objects.equals(fullName, users.fullName) &&
                Objects.equals(registrationNumber, users.registrationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rolesId, customersId, username, password, fullName, registrationNumber);
    }
}
