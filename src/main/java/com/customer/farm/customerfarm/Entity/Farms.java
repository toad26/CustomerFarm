package com.customer.farm.customerfarm.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "farms", schema = "customer_farm")
public class Farms implements Serializable {
    private Long id;
    @JsonBackReference
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

    @ManyToMany(mappedBy = "farms", fetch = FetchType.LAZY)
    public List<Users> getUsers() {
        return users;
    }
    public void setUsers(List<Users> users) {
        this.users = users;
    }
}