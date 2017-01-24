package com.uts.ostp.sample.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Sample entity
 */
@Entity
@Table(name = Sample.TABLEN_NAME)
public class Sample {

    static final String TABLEN_NAME = "samples";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private String name;

    public Sample() {
        // Required by JPA
    }

    public Sample(final String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Sample{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }
}
