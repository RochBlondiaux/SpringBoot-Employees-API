package com.roch.employees.api.service;

import com.roch.employees.api.model.Job;
import com.roch.employees.api.repository.JobRepository;
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

    public Job create(@NonNull Job job) {
        return repository.save(job);
    }

    public void update(@NonNull Job job) {
        repository.save(job);
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
