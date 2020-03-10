package com.customer.farm.customerfarm.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedNativeQuery(name = "getById", query = "SELECT * FROM customers WHERE id=:customersId")
@Table(name = "customers", schema = "customer_farm")
public class Customers  implements Serializable {
    private Long id;
    private String name;
    private String email;
    private String address;

    @JsonIgnore
    private List<Farms> farms = new ArrayList<>(0);

    @JsonIgnore
    private List<Users> users = new ArrayList<>(0);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customersId")
    public List<Farms> getFarms() {
        return farms;
    }

    public void setFarms(List<Farms> farms) {
        this.farms = farms;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customersId")
    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

    @Column(name = "name", nullable = false, length = 150)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "email", nullable = true, length = 150)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "address", nullable = true, length = 150)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Customers that = (Customers) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(email, that.email)
                && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, address);
    }

    @Override
    public String toString() {
        return "Customers{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", farms=" + farms +
                ", users=" + users +
                '}';
    }
}