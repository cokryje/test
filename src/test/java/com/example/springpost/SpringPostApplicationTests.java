package com.example.springpost;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SpringPostApplicationTests {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnOK_whenGettingListOfEmployees() throws Exception {
        mockMvc.perform(get("/showEmployees")).andExpect(status().isOk());
    }

    @Test
    void shouldReturnOK_whenPostingCorrectEmployeeData() throws Exception {
        Employee employee = new Employee("name", "surname", new Date());
        String body = new ObjectMapper().writeValueAsString(employee);

        mockMvc.perform(post("/addEmployee").contentType(MediaType.APPLICATION_JSON)
                .content(body)).andExpect(status().isOk());
    }

    @Test
    void shouldReturn400_whenPostingWithoutData() throws Exception {
        mockMvc.perform(post("/addEmployee"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn400_whenPostingIncorrectData() throws Exception {
        mockMvc.perform(post("/addEmployee").contentType(MediaType.APPLICATION_JSON).content("123"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnListOfEmployees_whenGettingListOfEmployees() throws Exception {
        Employee employee1 = new Employee("name", "surname", new Date(123, 1, 1));
        Employee employee2 = new Employee("name2", "surname2", new Date(123, 1, 1));
        Employee employee3 = new Employee("name3", "surname3", new Date(123, 1, 1));

        String body1 = new ObjectMapper().writeValueAsString(employee1);
        String body2 = new ObjectMapper().writeValueAsString(employee2);
        String body3 = new ObjectMapper().writeValueAsString(employee3);

        ArrayList<Employee> list = new ArrayList<>();
        list.add(employee1);
        list.add(employee2);
        list.add(employee3);
        String expected = new ObjectMapper().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).writeValueAsString(list);

        mockMvc.perform(post("/addEmployee").contentType(MediaType.APPLICATION_JSON).content(body1));
        mockMvc.perform(post("/addEmployee").contentType(MediaType.APPLICATION_JSON).content(body2));
        mockMvc.perform(post("/addEmployee").contentType(MediaType.APPLICATION_JSON).content(body3));

        String get;
        get = mockMvc.perform(get("/showEmployees")).andReturn().getResponse().getContentAsString();

        Assertions.assertEquals(expected, get);
    }
}
