package com.customer.farm.customerfarm.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedNativeQuery(name = "findUserByUsername", query = "SELECT * FROM users WHERE username=:username")
@NamedNativeQuery(name = "findUserByUsernameAndPassword", query = "SELECT * FROM users WHERE username=:username and password=:password")
@Table(name = "users", schema = "customer_farm")
public class Users implements Serializable {
    private Long id;
    private Roles rolesId;
    @JsonBackReference
    private Customers customersId;
    private String name;
    private String username;
    private String password;
    private String address;
    private List<Farms> farms = new ArrayList<>(0);

    public Users() {}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "roles_id", referencedColumnName = "id")
    public Roles getRolesId() {
        return rolesId;
    }
    public void setRolesId(Roles rolesId) {
        this.rolesId = rolesId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customers_id")
    public Customers getCustomersId() {
        return customersId;
    }
    public void setCustomersId(Customers customersId) {
        this.customersId = customersId;
    }

    @Column(name = "name", nullable = false, length = 150)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "username", nullable = false, length = 50)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password", nullable = false, length = 150)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "address", nullable = true, length = 150)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "users_farms", joinColumns = {
            @JoinColumn(name = "users_id", nullable = false, updatable = true) }, inverseJoinColumns = {
            @JoinColumn(name = "farms_id", nullable = false, updatable = true) })
    public List<Farms> getFarms() {
        return farms;
    }
    public void setFarms(List<Farms> farms) {
        this.farms = farms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users that = (Users) o;
        return id == that.id &&
                rolesId == that.rolesId &&
                customersId == that.customersId &&
                Objects.equals(name, that.name) &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rolesId, customersId, name, username, password, address);
    }
}
