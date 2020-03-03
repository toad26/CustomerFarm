package com.customer.farm.customerfarm.Entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Farms {
    private Integer id;
    private Integer customersId;
    private Integer usersId;
    private String farmName;
    private Integer farmValue;

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
    @Column(name = "users_id", nullable = false)
    public Integer getUsersId() {
        return usersId;
    }

    public void setUsersId(int usersId) {
        this.usersId = usersId;
    }

    public void setUsersId(Integer usersId) {
        this.usersId = usersId;
    }

    @Basic
    @Column(name = "farm_name", nullable = true, length = 255)
    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    @Basic
    @Column(name = "farm_value", nullable = true)
    public Integer getFarmValue() {
        return farmValue;
    }

    public void setFarmValue(Integer farmValue) {
        this.farmValue = farmValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Farms farms = (Farms) o;
        return Objects.equals(id, farms.id) &&
                Objects.equals(customersId, farms.customersId) &&
                Objects.equals(usersId, farms.usersId) &&
                Objects.equals(farmName, farms.farmName) &&
                Objects.equals(farmValue, farms.farmValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customersId, usersId, farmName, farmValue);
    }
}
