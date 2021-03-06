package com.roch.employees.api.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 */
@Data
@Entity
@Table(name = "jobs")
public class Job {

    @Id
    private String name;

    private double salary;

    public Job() {
    }
}
