package com.roch.employees.api.controller;

import com.roch.employees.api.exceptions.JobNotFoundException;
import com.roch.employees.api.model.Job;
import com.roch.employees.api.service.JobService;
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
public class JobController {

    @Autowired
    private JobService service;

    /**
     * Get all existing jobs.
     *
     * @return An iterable object of Job.
     */
    @GetMapping("/jobs")
    public Iterable<Job> getJobs() {
        return service.getJobs();
    }

    /**
     * Get a job by its name.
     *
     * @param name job's name.
     * @return A filled job object if it exists, otherwise it throws an exception.
     */
    @GetMapping("/jobs/{name}")
    public Job getJob(@NonNull @PathVariable("name") String name) {
        return service.getByName(name)
                .orElseThrow(() -> new JobNotFoundException(String.format("Cannot find any job with name '%s'!", name)));
    }

    /**
     * Create a new job.
     *
     * @param job Job to be saved.
     * @return HTTP response status code with the created job.
     */
    @PostMapping("/jobs")
    public ResponseEntity<Void> createJob(@RequestBody Job job) {
        Job job1 = service.create(job);
        if (Objects.isNull(job1))
            return ResponseEntity.noContent().build();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{name}")
                .buildAndExpand(job1.getName())
                .toUri();
        return ResponseEntity.created(location)
                .build();
    }

    /**
     * Delete a job.
     *
     * @param job job to delete.
     * @return HTTP response status code, "ok" (200) if the job was deleted successfully, otherwise "no content".
     */
    @DeleteMapping("/jobs")
    public ResponseEntity<Void> deleteJob(@RequestBody Job job) {
        if (service.getByName(job.getName()).isEmpty())
            return ResponseEntity.noContent().build();
        service.delete(job);
        return ResponseEntity.ok()
                .build();
    }
}
