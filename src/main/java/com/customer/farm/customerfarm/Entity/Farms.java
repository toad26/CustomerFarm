package com.customer.farm.customerfarm.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "farms", schema = "customer_farm")
public class Farms {
    private Long id;
    private Customers customersId;
    private String name;
    private String address;
    private List<Users> users = new ArrayList<>(0);

    public Farms() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Column(name = "address", nullable = true, length = 150)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "users_farms", joinColumns = {
            @JoinColumn(name = "farms_id", nullable = false, updatable = true) }, inverseJoinColumns = {
            @JoinColumn(name = "users_id", nullable = false, updatable = true) })
    public List<Users> getUsers() {
        return users;
    }
    public void setUsers(List<Users> users) {
        this.users = users;
    }
}
