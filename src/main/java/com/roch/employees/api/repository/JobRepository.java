package com.roch.employees.api.repository;

import com.roch.employees.api.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 */
@Repository
public interface JobRepository extends JpaRepository<Job, String> {
}
