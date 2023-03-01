package com.ob.springexercise4.service.impl;

import com.ob.springexercise4.entity.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //@SpringBootTest -> recibe peticiones htttp // web enviroment puerto aleatorio
class LaptopServiceImplTest {

    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("Http://localhost:"+port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @Test
    void save() {
    }

    @Test
    void findById() {

        ResponseEntity<Laptop> response = testRestTemplate.getForEntity( "/laptops/get/{id}", Laptop.class);
    }

    @Test
    void findAll() {
       ResponseEntity<Laptop[]> response = testRestTemplate.getForEntity("/laptops/getAll", Laptop[].class);

       assertEquals(HttpStatus.OK , response.getStatusCode());
       assertEquals(200, response.getStatusCodeValue());

       List<Laptop> responseLaptops = Arrays.asList(response.getBody());

       assertNotEquals();

    }
}