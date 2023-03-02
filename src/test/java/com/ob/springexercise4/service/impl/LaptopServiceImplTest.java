package com.ob.springexercise4.service.impl;

import com.ob.springexercise4.entity.Laptop;
import com.ob.springexercise4.repository.LaptopRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //@SpringBootTest -> recibe peticiones htttp // web enviroment puerto aleatorio
class LaptopServiceImplTest {

    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    LaptopRepository laptopRepository;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("Http://localhost:"+port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @DisplayName("Test the creating and save of a Laptop entityt")
    @Test
    void save() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                {
                    "brand": "Lenovo",
                    "processor": "ryzen 3",
                    "memory": "8gb",
                    "disk": "256gb",
                    "integratedGraphics": true
                }
                """;

        HttpEntity<String> request = new HttpEntity<>(json,headers);

        ResponseEntity<Laptop> response = testRestTemplate.exchange("/laptops/save/", HttpMethod.POST, request, Laptop.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("ryzen 3", response.getBody().getProcessor());
        assertNotNull(response.getBody().getId());

        Laptop savedLaptop = laptopRepository.findById(response.getBody().getId()).orElse(null);

        assertNotNull(savedLaptop.getId());
        assertEquals("ryzen 3",savedLaptop.getProcessor());


    }

    @Test
    void findByIdTest() {
        //Arrange

        Laptop laptop = new Laptop("gigabyte","i7","32gb","4Tb",false);

        laptopRepository.save(laptop);

        //Act

        ResponseEntity<Laptop> response = testRestTemplate.getForEntity( "/laptops/get/{id}", Laptop.class, laptop.getId()); //  TODO

        //Assert

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(response.getBody().getId(),laptop.getId());
        assertEquals(response.getBody().getProcessor() , laptop.getProcessor());

    }

    @Test
    void findAll() {

        //Arrange
        Laptop laptop = new Laptop("gigabyte", "i7", "32gb", "4Tb", false);
        Laptop laptop2 = new Laptop("Toshiba", "ryzen 7", "32gb", "4Tb", false);

        laptopRepository.saveAll(Arrays.asList(laptop, laptop2));

        //Act
       ResponseEntity<List<Laptop>> response = testRestTemplate.exchange("/laptops/getAll", HttpMethod.GET, null, new ParameterizedTypeReference<List<Laptop>>() {});



       //Assert

        List<Laptop> laptops = response.getBody();

       assertEquals(HttpStatus.OK , response.getStatusCode());
       assertEquals(200, response.getStatusCodeValue());

       assertEquals(2,laptops.size());

       assertEquals(laptop.getProcessor(), laptops.get(0).getProcessor());
       assertEquals(laptop.getMemory(), laptops.get(0).getMemory());
       assertEquals(laptop.getBrand(), laptops.get(0).getBrand());


       assertEquals(laptop2.getProcessor(), laptops.get(1).getProcessor());
       assertEquals(laptop2.getMemory(), laptops.get(1).getMemory());
        assertEquals(laptop2.getBrand(), laptops.get(1).getBrand());




    }
}