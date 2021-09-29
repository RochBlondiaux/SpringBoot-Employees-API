package com.roch.employees.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.roch.employees.model.Employee;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

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
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static Employee validEmployee;
    private static Employee invalidEmployee;
    private static ObjectWriter mapper;

    @BeforeAll
    static void beforeAll() {
        validEmployee = new Employee();
        validEmployee.setFirstName("Test");
        validEmployee.setLastName("Spring");
        validEmployee.setMail("test@roch-blondiaux.com");
        validEmployee.setHireDate(new Date());
        validEmployee.setPassword("My Super Password");

        invalidEmployee = new Employee();

        mapper = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer()
                .withDefaultPrettyPrinter();
    }

    @BeforeEach
    void setUp() {
        assert validEmployee != null;
    }

    @Test
    @Order(1)
    public void testCreateEmployee() throws Exception {
        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapper.writeValueAsString(validEmployee))
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andExpect(status().isCreated());
        validEmployee.setId(1);
    }

    @Test
    @Order(2)
    public void testGetSpecificEmployee() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("firstName").value("Test"))
                .andExpect(jsonPath("lastName").value("Spring"))
                .andExpect(jsonPath("mail").value("test@roch-blondiaux.com"));
    }

    @Test
    @Order(3)
    public void testUpdateEmployee() throws Exception {
        validEmployee.setFirstName("Hulk");
        mockMvc.perform(patch("/employees")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapper.writeValueAsString(validEmployee))
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("firstName").value("Hulk"));
    }

    @Test
    @Order(4)
    public void testGetEmployees() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("Hulk"))
                .andExpect(jsonPath("$[0].lastName").value("Spring"))
                .andExpect(jsonPath("$[0].mail").value("test@roch-blondiaux.com"));
    }

    @Test
    @Order(5)
    public void testDeleteEmployee() throws Exception {
        mockMvc.perform(delete("/employees")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapper.writeValueAsString(validEmployee))
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/employees/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @Order(6)
    public void testDeleteInvalidEmployee() throws Exception {
        mockMvc.perform(delete("/employees")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapper.writeValueAsString(invalidEmployee))
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(7)
    public void testGetInvalidEmployee() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/134"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @Order(8)
    public void testUpdateInvalidEmployee() throws Exception {
        mockMvc.perform(patch("/employees")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapper.writeValueAsString(invalidEmployee))
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andExpect(status().isNotFound());
    }
}
