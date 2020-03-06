package com.customer.farm.customerfarm.Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users_farms", schema = "customer_farm")
public class UsersFarms {
    private Long id;
    private Long usersId;
    private Long farmsId;

    public UsersFarms() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Column(name = "users_id", nullable = false, length = 11)
    public Long getUsersId() {
        return usersId;
    }

    public void setUsersId(Long usersId) {
        this.usersId = usersId;
    }
    @Column(name = "farms_id", nullable = false, length = 11)
    public Long getFarmsId() {
        return farmsId;
    }

    public void setFarmsId(Long farmsId) {
        this.farmsId = farmsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersFarms that = (UsersFarms) o;
        return usersId == that.usersId &&
                farmsId == that.farmsId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(usersId, farmsId);
    }
}
