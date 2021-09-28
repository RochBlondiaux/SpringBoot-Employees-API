package com.roch.employees.controller;

import com.roch.employees.exceptions.JobNotFoundException;
import com.roch.employees.model.Job;
import com.roch.employees.service.JobService;
import lombok.Data;
import lombok.NonNull;
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
    @GetMapping("/job/{name}")
    public Job getJob(@NonNull @PathVariable("name") String name) {
        return service.getByName(name)
                .orElseThrow(() -> new JobNotFoundException(String.format("Cannot find any job with name '%s'!", name)));
    }
}
