package com.aripd.bizibee.entity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.eclipse.persistence.annotations.Multitenant;
import org.eclipse.persistence.annotations.MultitenantType;
import org.eclipse.persistence.annotations.TenantDiscriminatorColumn;
import org.eclipse.persistence.annotations.TenantDiscriminatorColumns;

@Entity
@Multitenant(value = MultitenantType.SINGLE_TABLE)
@TenantDiscriminatorColumns({
    @TenantDiscriminatorColumn(name = "SIMULATION_ID", contextProperty = "eclipselink.tenant-id")
    ,
    @TenantDiscriminatorColumn(name = "USER_ID", contextProperty = "eclipselink.tenant-user-id")
})
@Cacheable(false)
public class ResponseEntity extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "SIMULATION_ID", insertable = false, updatable = false)
    private SimulationEntity simulation;

    @ManyToOne
    @JoinColumn(name = "USER_ID", insertable = false, updatable = false)
    private UserEntity user;

    @NotNull
    @JoinColumn(nullable = false)
    @ManyToOne
    private DecisionEntity decision;

    @Column(columnDefinition = "TEXT")
    private String outcome;

    public ResponseEntity() {
    }

    public ResponseEntity(DecisionEntity decision, String outcome) {
        this.decision = decision;
        this.outcome = outcome;
    }

    @Transient
    public double getScore() {
        double score = 0;
        switch (decision.getDecisionType()) {
            case SINGLE_CHOICE:
                score += decision.getGm();
                break;
            case MULTIPLE_CHOICE:
                score += decision.getGm();
                break;
            case SINGLE_SKU_LISTING:
                score += decision.getGm();
                break;
            case MULTIPLE_SKU_LISTING:
                score += decision.getGm();
                break;
            case RANGE_SKU_LISTING:
                score += decision.getGm();
                break;
            case SINGLE_CHOICE_SKU_LISTING:
                score += decision.getGm();
                break;
            case MULTIPLE_CHOICE_SKU_LISTING:
                score += decision.getGm();
                break;
        }
        return score;
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
