package com.roch.employees.model;

import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 */
@Data
@Table(name = "employees")
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Setter
    private String mail;

    @Setter
    private String password;

    @Setter
    @JoinTable(name = "employees_jobs", joinColumns = @JoinColumn(name="id"))
    @OneToOne
    private Job job;

    @Temporal(TemporalType.DATE)
    @Column(name = "hire_date", nullable = false)
    private Date hireDate;

    public Employee() {
    }
}
