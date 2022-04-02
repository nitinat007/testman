package com.testman.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Author: kunitin
 * Created: 19/03/22
 * Info: An abstract class to be extended to any class that needs tracing changes
 **/

@MappedSuperclass //this allows entities to inherit properties from this class
@EntityListeners(AuditingEntityListener.class) // listener to automatically populates the fields
@Data
@JsonIgnoreProperties(
        value = {"createdAt", "updatedAt"},
        allowGetters = true
)
public abstract class AuditExtensionEntity implements Serializable {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    private Date updatedAt;

    //@LastModifiedBy
    // private User user; who did this change

}
