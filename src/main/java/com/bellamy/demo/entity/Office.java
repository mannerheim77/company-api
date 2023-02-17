package com.bellamy.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@Table(name="office")
public class Office implements Serializable {

    public Office(String name) {
        this.name = name;
    }

    public Office(String name, Company company) {
        this.name = name;
        this.company = company;
    }

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, cascade= CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name="office_name", nullable=false)
    private String name;

    @Column(name="record_create_ts")
    @CreationTimestamp
    private Timestamp recordCreateTs;

    @Column(name="record_update_ts")
    @UpdateTimestamp
    private Timestamp recordUpdateTs;

    @OneToMany(mappedBy="office", fetch= FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true)
    private List<Employee> employees;
}
