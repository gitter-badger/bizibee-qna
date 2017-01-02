package com.aripd.bizibee.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class SimulationEntity extends AbstractEntity {

    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateStart;

    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEnd;

    @NotNull
    @Column(nullable = false)
    private String code;

    @NotNull
    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String thanks;

    private double salesStart;
    private double budgetStart;
    private double gmStart;
    private double msStart;

    private double gmWeighted;
    private double msWeighted;
    private double usgWeighted;

    @OneToMany(mappedBy = "simulation", orphanRemoval = true)
    private List<UserEntity> users;

    public SimulationEntity() {
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThanks() {
        return thanks;
    }

    public void setThanks(String thanks) {
        this.thanks = thanks;
    }

    public double getSalesStart() {
        return salesStart;
    }

    public void setSalesStart(double salesStart) {
        this.salesStart = salesStart;
    }

    public double getBudgetStart() {
        return budgetStart;
    }

    public void setBudgetStart(double budgetStart) {
        this.budgetStart = budgetStart;
    }

    public double getGmStart() {
        return gmStart;
    }

    public void setGmStart(double gmStart) {
        this.gmStart = gmStart;
    }

    public double getMsStart() {
        return msStart;
    }

    public void setMsStart(double msStart) {
        this.msStart = msStart;
    }

    public double getGmWeighted() {
        return gmWeighted;
    }

    public void setGmWeighted(double gmWeighted) {
        this.gmWeighted = gmWeighted;
    }

    public double getMsWeighted() {
        return msWeighted;
    }

    public void setMsWeighted(double msWeighted) {
        this.msWeighted = msWeighted;
    }

    public double getUsgWeighted() {
        return usgWeighted;
    }

    public void setUsgWeighted(double usgWeighted) {
        this.usgWeighted = usgWeighted;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

}
