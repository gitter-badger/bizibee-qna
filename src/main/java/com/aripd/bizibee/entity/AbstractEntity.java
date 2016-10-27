package com.aripd.bizibee.entity;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlElement;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlElement
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Basic(optional = false)
    //@Column(nullable = false, columnDefinition = "BIGINT UNSIGNED")
    protected Long id;

    public AbstractEntity() {
    }

    //@Version
    //private Long version;
    public Long getId() {
        return id;
    }

    //public Long getVersion() {
    //return version;
    //}
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (!(obj instanceof AbstractEntity)) {
            return false;
        } else if (((AbstractEntity) obj).id == null) {
            return false;
        } else {
            return ((AbstractEntity) obj).id.equals(this.id);
        }
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "[ id=" + id + " ]";
    }
}
