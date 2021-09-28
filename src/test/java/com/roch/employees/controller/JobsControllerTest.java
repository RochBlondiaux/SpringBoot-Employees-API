package com.roch.employees.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.roch.employees.model.Job;
import com.roch.employees.service.JobService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JobsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JobService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Order(1)
    public void testCreateJob() throws Exception {
        Job job;
        job = new Job();
        job.setName("Collaborator");
        job.setSalary(10000);

        ObjectMapper mapper = new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString( job);

        mockMvc.perform(post("/jobs")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(requestJson))
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andExpect(status().isCreated());
    }

    @Test
    @Order(2)
    public void testGetJob() throws Exception {
        mockMvc.perform(get("/job/CTO"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("salary").value(20000));
    }

    @Test
    @Order(3)
    public void testGetJobs() throws Exception {
        mockMvc.perform(get("/jobs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Collaborator"))
                .andExpect(jsonPath("$[1].salary").value(20000));
    }

}
