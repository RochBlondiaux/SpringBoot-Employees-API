package com.roch.employees.api.repository;

import com.roch.employees.api.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
