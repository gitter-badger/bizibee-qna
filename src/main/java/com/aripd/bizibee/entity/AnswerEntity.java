package com.aripd.bizibee.entity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.eclipse.persistence.annotations.Multitenant;
import org.eclipse.persistence.annotations.MultitenantType;
import org.eclipse.persistence.annotations.TenantDiscriminatorColumn;

@Entity
@Multitenant(value = MultitenantType.SINGLE_TABLE)
@TenantDiscriminatorColumn(name = "SIMULATION_ID", contextProperty = "eclipselink.tenant-id")
@Cacheable(false)
public class AnswerEntity extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "SIMULATION_ID", insertable = false, updatable = false)
    private SimulationEntity simulation;

    @NotNull
    @Column(nullable = false, columnDefinition = "TEXT")
    private String name;

    @Lob
    private byte[] bytes;

    @NotNull
    @JoinColumn(nullable = false)
    @ManyToOne
    private QuestionEntity question;

    private int coefScore = 0;

    private double coefBudget = 0;

    private int coefIndexMin = 0;
    private int coefIndexMax = 0;
    private int coefIndexStep = 1;

    private double coefUsg = 0;
    private double coefUsgGainMin = 0;
    private double coefUsgGainMax = 0;

    private double coefGm = 0;
    private double coefGmGainMin = 0;
    private double coefGmGainMax = 0;

    private double coefMs = 0;
    private int coefMsBreakpointIndexMin = 0;
    private int coefMsBreakpointIndexMax = 0;
    private int coefMsOptimalIndexMax = 0;
    private double coefMsGainMin = 0;
    private double coefMsGainMax = 0;

    public AnswerEntity() {
    }

    @Transient
    public int getInputSize() {
        return (int) (Math.log10(coefIndexMax) + 1);
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

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public QuestionEntity getQuestion() {
        return question;
    }

    public void setQuestion(QuestionEntity question) {
        this.question = question;
    }

    public int getCoefScore() {
        return coefScore;
    }

    public void setCoefScore(int coefScore) {
        this.coefScore = coefScore;
    }

    public double getCoefBudget() {
        return coefBudget;
    }

    public void setCoefBudget(double coefBudget) {
        this.coefBudget = coefBudget;
    }

    public int getCoefIndexMin() {
        return coefIndexMin;
    }

    public void setCoefIndexMin(int coefIndexMin) {
        this.coefIndexMin = coefIndexMin;
    }

    public int getCoefIndexMax() {
        return coefIndexMax;
    }

    public void setCoefIndexMax(int coefIndexMax) {
        this.coefIndexMax = coefIndexMax;
    }

    public int getCoefIndexStep() {
        return coefIndexStep;
    }

    public void setCoefIndexStep(int coefIndexStep) {
        this.coefIndexStep = coefIndexStep;
    }

    public double getCoefUsg() {
        return coefUsg;
    }

    public void setCoefUsg(double coefUsg) {
        this.coefUsg = coefUsg;
    }

    public double getCoefUsgGainMin() {
        return coefUsgGainMin;
    }

    public void setCoefUsgGainMin(double coefUsgGainMin) {
        this.coefUsgGainMin = coefUsgGainMin;
    }

    public double getCoefUsgGainMax() {
        return coefUsgGainMax;
    }

    public void setCoefUsgGainMax(double coefUsgGainMax) {
        this.coefUsgGainMax = coefUsgGainMax;
    }

    public double getCoefGm() {
        return coefGm;
    }

    public void setCoefGm(double coefGm) {
        this.coefGm = coefGm;
    }

    public double getCoefGmGainMin() {
        return coefGmGainMin;
    }

    public void setCoefGmGainMin(double coefGmGainMin) {
        this.coefGmGainMin = coefGmGainMin;
    }

    public double getCoefGmGainMax() {
        return coefGmGainMax;
    }

    public void setCoefGmGainMax(double coefGmGainMax) {
        this.coefGmGainMax = coefGmGainMax;
    }

    public double getCoefMs() {
        return coefMs;
    }

    public void setCoefMs(double coefMs) {
        this.coefMs = coefMs;
    }

    public int getCoefMsBreakpointIndexMin() {
        return coefMsBreakpointIndexMin;
    }

    public void setCoefMsBreakpointIndexMin(int coefMsBreakpointIndexMin) {
        this.coefMsBreakpointIndexMin = coefMsBreakpointIndexMin;
    }

    public int getCoefMsBreakpointIndexMax() {
        return coefMsBreakpointIndexMax;
    }

    public void setCoefMsBreakpointIndexMax(int coefMsBreakpointIndexMax) {
        this.coefMsBreakpointIndexMax = coefMsBreakpointIndexMax;
    }

    public int getCoefMsOptimalIndexMax() {
        return coefMsOptimalIndexMax;
    }

    public void setCoefMsOptimalIndexMax(int coefMsOptimalIndexMax) {
        this.coefMsOptimalIndexMax = coefMsOptimalIndexMax;
    }

    public double getCoefMsGainMin() {
        return coefMsGainMin;
    }

    public void setCoefMsGainMin(double coefMsGainMin) {
        this.coefMsGainMin = coefMsGainMin;
    }

    public double getCoefMsGainMax() {
        return coefMsGainMax;
    }

    public void setCoefMsGainMax(double coefMsGainMax) {
        this.coefMsGainMax = coefMsGainMax;
    }

}
