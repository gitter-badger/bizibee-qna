package com.aripd.bizibee.entity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.eclipse.persistence.annotations.Multitenant;
import org.eclipse.persistence.annotations.MultitenantType;
import org.eclipse.persistence.annotations.TenantDiscriminatorColumn;

@Entity
@Multitenant(value = MultitenantType.SINGLE_TABLE)
@TenantDiscriminatorColumn(name = "SIMULATION_ID", contextProperty = "eclipselink.tenant-id")
@Cacheable(false)
public class ProductEntity extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "SIMULATION_ID", insertable = false, updatable = false)
    private SimulationEntity simulation;

    @NotNull
    @Column(nullable = false)
    private String name;

    private double gm;
    private double ms;
    private double usg;
    
    private int indexMin;
    private int indexMax;
    private int indexStep;

    public ProductEntity() {
    }

    public SimulationEntity getSimulation() {
        return simulation;
    }

    public void setSimulation(SimulationEntity simulation) {
        this.simulation = simulation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getUsg() {
        return usg;
    }

    public void setUsg(double usg) {
        this.usg = usg;
    }

    public double getGm() {
        return gm;
    }

    public void setGm(double gm) {
        this.gm = gm;
    }

    public double getMs() {
        return ms;
    }

    public void setMs(double ms) {
        this.ms = ms;
    }

    public int getIndexMin() {
        return indexMin;
    }

    public void setIndexMin(int indexMin) {
        this.indexMin = indexMin;
    }

    public int getIndexMax() {
        return indexMax;
    }

    public void setIndexMax(int indexMax) {
        this.indexMax = indexMax;
    }

    public int getIndexStep() {
        return indexStep;
    }

    public void setIndexStep(int indexStep) {
        this.indexStep = indexStep;
    }

}
