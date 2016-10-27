package com.aripd.bizibee.entity;

import java.util.List;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@TenantDiscriminatorColumn(name = "COMPANY_ID", contextProperty = "eclipselink.tenant-id")
@Cacheable(false)
public class DecisionEntity extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "COMPANY_ID", insertable = false, updatable = false)
    private CompanyEntity company;

    @NotNull
    @Column(nullable = false)
    private String name;

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

    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
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

}
