package com.aripd.bizibee.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class TeamEntity extends AbstractEntity {

    @NotNull
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "team", orphanRemoval = true)
    private List<UserEntity> users;

    public TeamEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

}
