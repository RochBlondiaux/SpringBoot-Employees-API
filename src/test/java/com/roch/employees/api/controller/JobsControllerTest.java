package com.roch.employees.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.roch.employees.api.model.Job;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
public class JobsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static Job validJob;
    private static Job invalidJob;
    private static ObjectWriter mapper;

    @BeforeAll
    static void beforeAll() {
        validJob = new Job();
        validJob.setSalary(5300000);
        validJob.setName("CEO");

        invalidJob = new Job();
        invalidJob.setName("ZE");

        mapper = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer()
                .withDefaultPrettyPrinter();
    }

    @BeforeEach
    void setUp() {
        assert validJob != null;
    }

    @Test
    @Order(1)
    public void testCreateJob() throws Exception {
        mockMvc.perform(post("/jobs")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapper.writeValueAsString(validJob))
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andExpect(status().isCreated());
    }

    @Test
    @Order(2)
    public void testGetJob() throws Exception {
        mockMvc.perform(get("/jobs/" + validJob.getName()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("salary").value(validJob.getSalary()));
    }

    @Test
    @Order(3)
    public void testGetJobs() throws Exception {
        mockMvc.perform(get("/jobs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(validJob.getName()))
                .andExpect(jsonPath("$[0].salary").value(validJob.getSalary()));
    }

    @Test
    @Order(4)
    public void testDeleteJob() throws Exception {
        mockMvc.perform(delete("/jobs")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapper.writeValueAsString(validJob))
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(5)
    public void testGetDeletedJob() throws Exception {
        mockMvc.perform(get("/jobs/" + validJob.getName()))
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(5)
    public void testDeleteInvalidJob() throws Exception {
        mockMvc.perform(delete("/jobs/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(invalidJob))
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andExpect(status().isNoContent());
    }
}
