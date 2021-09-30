package com.roch.employees.api.service;

import com.roch.employees.api.model.Employee;
import com.roch.employees.api.repository.EmployeeRepository;
import lombok.Data;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 */
@Data
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    public Optional<Employee> getById(@NonNull int id) {
        return repository.findById(id);
    }

    public Employee save(@NonNull Employee employee) {
        return repository.save(employee);
    }

    public void delete(@NonNull int id) {
        repository.deleteById(id);
    }

    public Iterable<Employee> getEmployees() {
        return repository.findAll();
    }
}
