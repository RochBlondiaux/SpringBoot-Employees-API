package com.roch.employees.controller;

import com.roch.employees.exceptions.EmployeeNotFoundException;
import com.roch.employees.model.Employee;
import com.roch.employees.service.EmployeeService;
import lombok.Data;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Objects;

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
    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable("id") int id) {
        return service.getById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(String.format("Cannot find any employee with id '%d'!", id)));
    }

    /**
     * Create new employee.
     *
     * @param employee employee object to save.
     * @return HTTP response status code with the created employee url.
     */
    @PostMapping("/employees")
    public ResponseEntity<Void> createEmployee(@NonNull @RequestBody Employee employee) {
        Employee newEmployee = service.save(employee);
        if (Objects.isNull(newEmployee))
            return ResponseEntity.noContent().build();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newEmployee.getId())
                .toUri();
        return ResponseEntity.created(location)
                .build();
    }

    /**
     * Delete an existing employee.
     *
     * @param employee Employee object to delete.
     * @return HTTP response status code.
     */
    @DeleteMapping("/employees")
    public ResponseEntity<Void> deleteEmployee(@NonNull @RequestBody Employee employee) {
        if (service.getById(employee.getId()).isEmpty())
            return ResponseEntity.notFound().build();
        service.delete(employee.getId());
        return ResponseEntity.ok().build();
    }

    /**
     * Update an existing employee.
     *
     * @param employee to update.
     * @return updated employee object.
     */
    @PatchMapping("/employees")
    public Employee updateEmployee(@NonNull @RequestBody Employee employee) {
        if (service.getById(employee.getId()).isEmpty())
            throw new EmployeeNotFoundException(String.format("Cannot find any employee with id '%d'!", employee.getId()));
        return service.save(employee);
    }
}
