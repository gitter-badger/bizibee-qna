package com.aripd.bizibee.entity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import org.eclipse.persistence.annotations.Multitenant;
import org.eclipse.persistence.annotations.MultitenantType;
import org.eclipse.persistence.annotations.TenantDiscriminatorColumn;

@Entity
@Multitenant(value = MultitenantType.SINGLE_TABLE)
@TenantDiscriminatorColumn(name = "SIMULATION_ID", contextProperty = "eclipselink.tenant-id")
@Cacheable(false)
public class WeightEntity extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "SIMULATION_ID", insertable = false, updatable = false)
    private SimulationEntity simulation;

    @ManyToOne
    private DecisionEntity decision;

    @ManyToOne
    private SkuEntity sku;

    private double budget;
    private double gm;
    private double ms;
    private double usg;

    private int indexMin;
    private int indexMax;
    private int indexStep;

    private double usgGainMin;
    private double usgGainMax;

    private double gmGainMin;
    private double gmGainMax;

    private int msBreakpointIndexMin;
    private int msBreakpointIndexMax;
    private int msOptimalIndexMax;
    private double msGainMin;
    private double msGainMax;

    public WeightEntity() {
    }

    @Transient
    public int getInputSize() {
        return (int) (Math.log10(indexMax) + 1);
    }

    public SimulationEntity getSimulation() {
        return simulation;
    }

    public void setSimulation(SimulationEntity simulation) {
        this.simulation = simulation;
    }

    public double getUsg() {
        return usg;
    }

    public void setUsg(double usg) {
        this.usg = usg;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
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

    public double getUsgGainMin() {
        return usgGainMin;
    }

    public void setUsgGainMin(double usgGainMin) {
        this.usgGainMin = usgGainMin;
    }

    public double getUsgGainMax() {
        return usgGainMax;
    }

    public void setUsgGainMax(double usgGainMax) {
        this.usgGainMax = usgGainMax;
    }

    public double getGmGainMin() {
        return gmGainMin;
    }

    public void setGmGainMin(double gmGainMin) {
        this.gmGainMin = gmGainMin;
    }

    public double getGmGainMax() {
        return gmGainMax;
    }

    public void setGmGainMax(double gmGainMax) {
        this.gmGainMax = gmGainMax;
    }

    public int getMsBreakpointIndexMin() {
        return msBreakpointIndexMin;
    }

    public void setMsBreakpointIndexMin(int msBreakpointIndexMin) {
        this.msBreakpointIndexMin = msBreakpointIndexMin;
    }

    public int getMsBreakpointIndexMax() {
        return msBreakpointIndexMax;
    }

    public void setMsBreakpointIndexMax(int msBreakpointIndexMax) {
        this.msBreakpointIndexMax = msBreakpointIndexMax;
    }

    public int getMsOptimalIndexMax() {
        return msOptimalIndexMax;
    }

    public void setMsOptimalIndexMax(int msOptimalIndexMax) {
        this.msOptimalIndexMax = msOptimalIndexMax;
    }

    public double getMsGainMin() {
        return msGainMin;
    }

    public void setMsGainMin(double msGainMin) {
        this.msGainMin = msGainMin;
    }

    public double getMsGainMax() {
        return msGainMax;
    }

    public void setMsGainMax(double msGainMax) {
        this.msGainMax = msGainMax;
    }

    public DecisionEntity getDecision() {
        return decision;
    }

    public void setDecision(DecisionEntity decision) {
        this.decision = decision;
    }

    public SkuEntity getSku() {
        return sku;
    }

    public void setSku(SkuEntity sku) {
        this.sku = sku;
    }

}
