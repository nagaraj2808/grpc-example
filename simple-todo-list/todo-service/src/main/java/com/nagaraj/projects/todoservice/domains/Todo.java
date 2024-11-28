package com.nagaraj.projects.todoservice.domains;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "todos")
@Data
public class Todo {
    @Id
    private String todoId;

    private String title;

    private Boolean done;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creationTime")
    private Date creationTime;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastUpdated")
    private Date lastUpdated;

}
