package com.aripd.bizibee.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
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
    @Column(nullable = false, unique = true)
    private String uuid;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DecisionType decisionType;

    @NotNull
    @Column(nullable = false)
    private String name;

    private int sortOrder;

    private String description;

    @Column(columnDefinition = "TEXT")
    private String remark;

    @Lob
    private byte[] bytes;

    private boolean required;

    @OneToMany(mappedBy = "decision", orphanRemoval = true)
    private List<DecisionchoiceEntity> decisionchoices;

    @OneToMany(mappedBy = "decision", orphanRemoval = true)
    private List<WeightEntity> weights = new ArrayList<>();

    public DecisionEntity() {
    }

    @PrePersist
    protected void prePersist() {
        uuid = UUID.randomUUID().toString();
    }

    @Transient
    public List<SkuEntity> getSkus() {
        List<SkuEntity> skus = new ArrayList<>();
        for (WeightEntity weight : this.getWeights()) {
            skus.add(weight.getSku());
        }
        return skus;
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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public DecisionType getDecisionType() {
        return decisionType;
    }

    public void setDecisionType(DecisionType decisionType) {
        this.decisionType = decisionType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DecisionchoiceEntity> getDecisionchoices() {
        return decisionchoices;
    }

    public void setDecisionchoices(List<DecisionchoiceEntity> decisionchoices) {
        this.decisionchoices = decisionchoices;
    }

    public List<WeightEntity> getWeights() {
        return weights;
    }

    public void setWeights(List<WeightEntity> weights) {
        this.weights = weights;
    }

}
