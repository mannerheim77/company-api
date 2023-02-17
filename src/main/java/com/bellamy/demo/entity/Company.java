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
@Table(name="company")
public class Company implements Serializable {

    public Company(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name="name", nullable=false)
    private String name;

    @Column(name="record_create_ts")
    @CreationTimestamp
    private Timestamp recordCreateTs;

    @Column(name="record_update_ts")
    @UpdateTimestamp
    private Timestamp recordUpdateTs;

    @OneToMany(mappedBy="company", fetch= FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true)
    private List<Office> offices;

}
