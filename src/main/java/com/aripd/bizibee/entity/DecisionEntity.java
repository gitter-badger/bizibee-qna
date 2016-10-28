package com.aripd.bizibee.entity;

import java.util.List;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.eclipse.persistence.annotations.Multitenant;
import org.eclipse.persistence.annotations.MultitenantType;
import org.eclipse.persistence.annotations.TenantDiscriminatorColumn;

@Entity
@Multitenant(value = MultitenantType.SINGLE_TABLE)
@TenantDiscriminatorColumn(name = "SIMULATION_ID", contextProperty = "eclipselink.tenant-id")
@Cacheable(false)
public class DecisionEntity extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "SIMULATION_ID", insertable = false, updatable = false)
    private SimulationEntity simulation;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DecisionType decisionType;

    @NotNull
    @Column(nullable = false)
    private String name;

    private double budget;

    private double gm;
    private double ms;
    private double usg;

    @OneToMany(mappedBy = "decision", orphanRemoval = true)
    private List<DecisionchoiceEntity> decisionchoices;

    public DecisionEntity() {
    }

    @Transient
    public int getAllUsgs() {
        int sum = 0;

        for (DecisionchoiceEntity s : decisionchoices) {
            sum += s.getUsg();
        }

        return sum;
    }

    @Transient
    public int getAllGms() {
        int sum = 0;

        for (DecisionchoiceEntity s : decisionchoices) {
            sum += s.getGm();
        }

        return sum;
    }

    @Transient
    public int getAllMss() {
        int sum = 0;

        for (DecisionchoiceEntity s : decisionchoices) {
            sum += s.getMs();
        }

        return sum;
    }

    public SimulationEntity getSimulation() {
        return simulation;
    }

    public void setSimulation(SimulationEntity simulation) {
        this.simulation = simulation;
    }

    public DecisionType getDecisionType() {
        return decisionType;
    }

    public void setDecisionType(DecisionType decisionType) {
        this.decisionType = decisionType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
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

    public List<DecisionchoiceEntity> getDecisionchoices() {
        return decisionchoices;
    }

    public void setDecisionchoices(List<DecisionchoiceEntity> decisionchoices) {
        this.decisionchoices = decisionchoices;
    }

}
