package com.aripd.bizibee.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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

    @ManyToOne
    private BrandEntity brand;

    @ManyToMany(mappedBy = "products", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH})
    private List<DecisionEntity> decisions = new ArrayList<>();

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

    public List<DecisionEntity> getDecisions() {
        return decisions;
    }

    public void setDecisions(List<DecisionEntity> decisions) {
        this.decisions = decisions;
    }

    public BrandEntity getBrand() {
        return brand;
    }

    public void setBrand(BrandEntity brand) {
        this.brand = brand;
    }

}
