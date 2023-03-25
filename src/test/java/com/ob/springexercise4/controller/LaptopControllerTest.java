package com.ob.springexercise4.controller;

import com.ob.springexercise4.entity.Laptop;
import com.ob.springexercise4.repository.LaptopRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //@SpringBootTest -> recibe peticiones htttp // web enviroment puerto aleatorio
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class LaptopControllerTest {


    @Value("${app.message}")
    String message;

    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    LaptopRepository laptopRepository;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("Http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @DisplayName("Test the creating and save of a Laptop entity")
    @Test
    @Rollback(value = false)
    void saveTest() {

        System.out.println( message );

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

        HttpEntity<String> request = new HttpEntity<>(json, headers);

        ResponseEntity<Laptop> response = testRestTemplate.exchange("/laptops/save/", HttpMethod.POST, request, Laptop.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("ryzen 3", response.getBody().getProcessor());
        assertNotNull(response.getBody().getId());

        Laptop savedLaptop = laptopRepository.findById(response.getBody().getId()).orElse(null);

        assertNotNull(savedLaptop.getId());
        assertEquals("ryzen 3", savedLaptop.getProcessor());


    }

    @DisplayName("Test getting a laptop by ID")
    @Test
    @Rollback(value = false)
    void findByIdTest() {
        //Arrange

        Laptop laptop = new Laptop("gigabyte", "i7", "32gb", "4Tb", false);

        laptopRepository.save(laptop);

        //Act

        ResponseEntity<Laptop> response = testRestTemplate.getForEntity("/laptops/get/{id}", Laptop.class, laptop.getId()); //  TODO

        //Assert

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(response.getBody().getId(), laptop.getId());
        assertEquals(response.getBody().getProcessor(), laptop.getProcessor());

        //laptopRepository.delete(laptop);


    }

    @DisplayName("Test getting the list of the existing laptops ")
    @Test
    void findAllTest() {

        //Arrange
        Laptop laptop = new Laptop("gigabyte", "i7", "32gb", "4Tb", false);
        Laptop laptop2 = new Laptop("Toshiba", "ryzen 7", "32gb", "4Tb", false);

        laptopRepository.saveAll(Arrays.asList(laptop, laptop2));

        //Act
        ResponseEntity<List<Laptop>> response = testRestTemplate.exchange("/laptops/getAll", HttpMethod.GET, null, new ParameterizedTypeReference<List<Laptop>>() {
        });


        //Assert

        List<Laptop> laptops = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());

        assertEquals(2, laptops.size());

        assertEquals(laptop.getProcessor(), laptops.get(0).getProcessor());
        assertEquals(laptop.getMemory(), laptops.get(0).getMemory());
        assertEquals(laptop.getBrand(), laptops.get(0).getBrand());


        assertEquals(laptop2.getProcessor(), laptops.get(1).getProcessor());
        assertEquals(laptop2.getMemory(), laptops.get(1).getMemory());
        assertEquals(laptop2.getBrand(), laptops.get(1).getBrand());

        laptopRepository.deleteAll();


    }

    @DisplayName("Test updating an existing laptop by ID")
    @Test
    @Rollback(value = false)
    void updateTest() {

        //Arrange

        Laptop laptop3 = new Laptop("Hp", "i5", "8gb", "256gb", true);
        Laptop savedLaptop = laptopRepository.save(laptop3);

        Laptop updatedLaptop = new Laptop("Dell", "i7", "16GB", "512GB", true);
        updatedLaptop.setId(savedLaptop.getId());

        Laptop nonExistingLaptop = new Laptop();
        nonExistingLaptop.setId(23454553);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        HttpEntity<Laptop> request = new HttpEntity<>(updatedLaptop, headers);
        HttpEntity<Laptop> request2 = new HttpEntity<>(nonExistingLaptop, headers);

        //Act

        ResponseEntity<Laptop> response = testRestTemplate.exchange("/laptops/{id}", HttpMethod.PUT, request, Laptop.class, savedLaptop.getId());


        //Assert

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedLaptop.getBrand(), response.getBody().getBrand());
        assertEquals(updatedLaptop.getProcessor(), response.getBody().getProcessor());
        assertEquals(updatedLaptop.getMemory(), response.getBody().getMemory());
        assertEquals(updatedLaptop.getDisk(), response.getBody().getDisk());
        assertEquals(updatedLaptop.isIntegratedGraphics(), response.getBody().isIntegratedGraphics());

        //laptopRepository.deleteAll();
    }

    @DisplayName("Test deleting of an existing laptop by ID")
    @Test
    @Rollback(value = false)
    void deleteTest() {

        Laptop laptop3 = new Laptop("Hp", "i5", "8gb", "256gb", true);
        laptop3.setId(1);
        Laptop savedLaptop = laptopRepository.save(laptop3);

        Laptop laptop4 = new Laptop("Bangho", "i5", "8gb", "256gb", true);
        laptop3.setId(2);
        Laptop savedLaptop2 = laptopRepository.save(laptop3);

        Long countLaptops = laptopRepository.count();

        ResponseEntity<Void> response = testRestTemplate.exchange("/laptops/1", HttpMethod.DELETE, null, Void.class);

        // assert that the response status code is 204 No Content
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        ResponseEntity<Long> countResponse = testRestTemplate.getForEntity("/laptops/count", Long.class);
        assertEquals(countLaptops - 1L, countResponse.getBody());

    }

    @DisplayName("Test deleting all the laptops")
    @Test
    @Rollback(value = false)
    void deleteAllTest() {

        //Arrange
        Laptop laptop = new Laptop("gigabyte", "i7", "32gb", "4Tb", false);
        Laptop laptop2 = new Laptop("Toshiba", "ryzen 7", "32gb", "4Tb", false);

        laptopRepository.saveAll(Arrays.asList(laptop, laptop2));

        //Act
        ResponseEntity<Void> response = testRestTemplate.exchange("/laptops/", HttpMethod.DELETE, null, Void.class);


        //Arrange

        assertEquals(HttpStatus.NO_CONTENT , response.getStatusCode());
        assertEquals(0, laptopRepository.count());


    }


}