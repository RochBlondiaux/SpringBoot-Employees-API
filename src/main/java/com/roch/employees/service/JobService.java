package com.roch.employees.service;

import com.roch.employees.model.Job;
import com.roch.employees.repository.JobRepository;
import lombok.Data;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 */
@Service
@Data
public class JobService {

    @Autowired
    private JobRepository repository;

    public Job create(@NonNull String name, @NonNull int salary) {
        Job job = new Job();
        job.setName(name);
        job.setSalary(salary);
        repository.save(job);
        return job;
    }

    public Optional<Job> getByName(@NonNull String name) {
        return repository.findById(name);
    }

    public void delete(@NonNull Job job) {
        repository.delete(job);
    }

    public Iterable<Job> getJobs() {
        return repository.findAll();
    }
}
