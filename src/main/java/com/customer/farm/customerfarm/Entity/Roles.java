package com.customer.farm.customerfarm.Entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "roles", schema = "customer_farm")
public class Roles implements Serializable {
    private Long id;
    private String name;
    private Users userId;

    public Roles() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}