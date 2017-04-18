package com.aripd.bizibee.entity;

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
import javax.validation.constraints.NotNull;
import org.eclipse.persistence.annotations.Multitenant;
import org.eclipse.persistence.annotations.MultitenantType;
import org.eclipse.persistence.annotations.TenantDiscriminatorColumn;

@Entity
@Multitenant(value = MultitenantType.SINGLE_TABLE)
@TenantDiscriminatorColumn(name = "SIMULATION_ID", contextProperty = "eclipselink.tenant-id")
@Cacheable(false)
public class QuestionEntity extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "SIMULATION_ID", insertable = false, updatable = false)
    private SimulationEntity simulation;

    @NotNull
    @Column(nullable = false, unique = true)
    private String uuid;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Kind kind;

    @NotNull
    @Column(nullable = false)
    private String name;

    private int sortOrder = 0;

    @Column(columnDefinition = "LONGTEXT")
    private String description;

    @Column(columnDefinition = "LONGTEXT")
    private String remark;

    @Lob
    private byte[] bytes;

    private boolean required = false;

    @OneToMany(mappedBy = "question", orphanRemoval = true)
    private List<AnswerEntity> answers;

    @ManyToOne
    private GroupEntity group;

    @ManyToOne
    private GuideEntity guide;
    
    private String confirmHeader;
    private String confirmMessage;
    
    private String notificationHeader;
    private String notificationMessage;

    private int coefScore = 0;
    private double coefBudget = 0;
    private double coefUsg = 0;
    private double coefGm = 0;
    private double coefMs = 0;

    public QuestionEntity() {
    }

    @PrePersist
    protected void prePersist() {
        uuid = UUID.randomUUID().toString();
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Kind getKind() {
        return kind;
    }

    public void setKind(Kind kind) {
        this.kind = kind;
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

    public List<AnswerEntity> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerEntity> answers) {
        this.answers = answers;
    }

    public GroupEntity getGroup() {
        return group;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }

    public GuideEntity getGuide() {
        return guide;
    }

    public void setGuide(GuideEntity guide) {
        this.guide = guide;
    }

    public String getConfirmHeader() {
        return confirmHeader;
    }

    public void setConfirmHeader(String confirmHeader) {
        this.confirmHeader = confirmHeader;
    }

    public String getConfirmMessage() {
        return confirmMessage;
    }

    public void setConfirmMessage(String confirmMessage) {
        this.confirmMessage = confirmMessage;
    }

    public String getNotificationHeader() {
        return notificationHeader;
    }

    public void setNotificationHeader(String notificationHeader) {
        this.notificationHeader = notificationHeader;
    }

    public String getNotificationMessage() {
        return notificationMessage;
    }

    public void setNotificationMessage(String notificationMessage) {
        this.notificationMessage = notificationMessage;
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

    public double getCoefUsg() {
        return coefUsg;
    }

    public void setCoefUsg(double coefUsg) {
        this.coefUsg = coefUsg;
    }

    public double getCoefGm() {
        return coefGm;
    }

    public void setCoefGm(double coefGm) {
        this.coefGm = coefGm;
    }

    public double getCoefMs() {
        return coefMs;
    }

    public void setCoefMs(double coefMs) {
        this.coefMs = coefMs;
    }

}
