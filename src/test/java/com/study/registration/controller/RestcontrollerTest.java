package com.study.registration.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.registration.entity.registration;
import com.study.registration.service.Service;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {Restcontroller.class})
@ExtendWith(SpringExtension.class)
class RestcontrollerTest {
    @Autowired
    private Restcontroller restcontroller;

    @MockBean
    private Service service;

    /**
     * Method under test: {@link Restcontroller#addnewstudent(registration)}
     */
    @Test
    void testAddnewstudent() throws Exception {
        doNothing().when(service).save((registration) any());

        registration registration = new registration();
        registration.setAmount(10);
        registration.setCourse(1);
        registration.setCredits(1);
        registration.setId(1);
        registration.setSudent_name("Sudent name");
        this.service.save(registration);
        String content = (new ObjectMapper()).writeValueAsString(registration);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/info/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(restcontroller)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":1,\"credits\":1,\"course\":1,\"amount\":10,\"sudent_name\":\"Sudent name\"}"));
    }

    /**
     * Method under test: {@link Restcontroller#delete(int)}
     */
    @Test
    void testDelete() throws Exception {
        registration registration = new registration();
        registration.setAmount(10);
        registration.setCourse(1);
        registration.setCredits(1);
        registration.setId(1);
        registration.setSudent_name("Sudent name");
        doNothing().when(service).delete(anyInt());
        when(service.findbyid(anyInt())).thenReturn(registration);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/info/delete/{myid}", 1);
        MockMvcBuilders.standaloneSetup(restcontroller)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("student has deleted with id:1"));
    }

    /**
     * Method under test: {@link Restcontroller#displayall()}
     */
    @Test
    void testDisplayall() throws Exception {
        when(service.findall()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/info/list");
        MockMvcBuilders.standaloneSetup(restcontroller)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link Restcontroller#searchbyid(int)}
     */
    @Test
    void testSearchbyid() throws Exception {
        registration registration = new registration();
        registration.setAmount(10);
        registration.setCourse(1);
        registration.setCredits(1);
        registration.setId(1);
        registration.setSudent_name("Sudent name");
        when(service.findbyid(anyInt())).thenReturn(registration);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/info/list/{myid}", 1);
        MockMvcBuilders.standaloneSetup(restcontroller)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":1,\"credits\":1,\"course\":1,\"amount\":10,\"sudent_name\":\"Sudent name\"}"));
    }

        /**
         * Method under test: {@link Restcontroller#updatestudent(registration)}
         */
    @Test
    void testUpdatestudent() throws Exception {
        doNothing().when(service).save((registration) any());

        registration registration = new registration();
        registration.setAmount(10);
        registration.setCourse(1);
        registration.setCredits(1);
        registration.setId(1);
        registration.setSudent_name("Sudent name");
        String content = (new ObjectMapper()).writeValueAsString(registration);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/info/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(restcontroller)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":1,\"credits\":1,\"course\":1,\"amount\":10,\"sudent_name\":\"Sudent name\"}"));
    }
}

