package com.roch.employees.controller;

import com.roch.employees.exceptions.EmployeeNotFoundException;
import com.roch.employees.model.Employee;
import com.roch.employees.service.EmployeeService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 */
@Data
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    /**
     * Get all existing employees.
     *
     * @return An iterable object of Employee fulfilled.
     */
    @GetMapping("/employees")
    public Iterable<Employee> getEmployees() {
        return service.getEmployees();
    }

    /**
     * Get an employee by its id.
     *
     * @param id employee id.
     * @return An employee object fulfilled, or otherwise it throws an exception.
     */
    @GetMapping("/employee/{id}")
    public Employee getEmployee(@PathVariable("id") int id) {
        return service.getById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(String.format("Cannot find any employee with id '%d'!", id)));
    }
}
