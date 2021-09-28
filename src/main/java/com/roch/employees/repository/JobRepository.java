package com.roch.employees.repository;

import com.roch.employees.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 */
public interface JobRepository extends JpaRepository<Job, String> {
}
