package com.roch.employees.repository;

import com.roch.employees.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 */
@Repository
public interface JobRepository extends JpaRepository<Job, String> {
}
