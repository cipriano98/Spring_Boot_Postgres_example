package com.zeta.postgres.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners( AuditingEntityListener.class )
@JsonIgnoreProperties(
    value = { "createdAt", "updateAt" },
    allowGetters = true
)
public abstract class AuditModel implements Serializable {
    @Temporal( TemporalType.TIMESTAMP )
    @Column( name = "created_at", nullable = false, updatable = false )
    @LastModifiedDate
    private Date createdAt;
    
    @Temporal( TemporalType.TIMESTAMP )
    @Column( name = "update_at", nullable = false )
    @LastModifiedDate
    private  Date updateAt;

    //getters and setters
    public Date getCreatedAt( ) {
        return this.createdAt;
    }

    public void setCreatedAt( Date createdAt ) {
        this.createdAt = createdAt;
    };

    public Date getUpdateAt( ) {
        return this.updateAt;
    }

    public void setUpdateAt( Date updateAt ) {
        this.updateAt = updateAt;
    };

    

}