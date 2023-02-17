package com.bellamy.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@Table(name="employee")
public class Employee implements Serializable {

    public Employee(String firstName, String lastName, String emailId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
    }

    public Employee(String firstName, String lastName, String emailId, Office office) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.office = office;
    }

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name="first_name", nullable=false)
    private String firstName;

    @Column(name="last_name", nullable=false)
    private String lastName;

    @Column(name="email_address", nullable=false)
    private String emailId;

    @Basic
    @Column(name="record_create_ts")
    @CreationTimestamp
    private Timestamp recordCreateTs;

    @Basic
    @Column(name="record_update_ts")
    @UpdateTimestamp
    private Timestamp recordUpdateTs;

    @ManyToOne(fetch = FetchType.LAZY, cascade= CascadeType.ALL)
    @JoinColumn(name = "office_id")
    private Office office;
}
