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
public class ResponseEntity extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "SIMULATION_ID", insertable = false, updatable = false)
    private SimulationEntity simulation;

    @NotNull
    @JoinColumn(nullable = false)
    @ManyToOne
    private UserEntity user;

    @NotNull
    @JoinColumn(nullable = false)
    @ManyToOne
    private DecisionEntity decision;

    @Column(columnDefinition = "TEXT")
    private String outcome;

    public ResponseEntity() {
    }

    public ResponseEntity(UserEntity user, DecisionEntity decision, String outcome) {
        this.user = user;
        this.decision = decision;
        this.outcome = outcome;
    }

    public SimulationEntity getSimulation() {
        return simulation;
    }

    public void setSimulation(SimulationEntity simulation) {
        this.simulation = simulation;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public DecisionEntity getDecision() {
        return decision;
    }

    public void setDecision(DecisionEntity decision) {
        this.decision = decision;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

}
