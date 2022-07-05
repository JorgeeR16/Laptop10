package com.example.obspringejercicio7.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.example.obspringejercicio10.Model.Laptop;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LaptopControllerTest {

    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri(("http://localhost:" + port));
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @Test
    void testCreate() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        String json = "{" +
                "'brand': 'Asus'," +
                "'model': 'xt1203'," +
                "'price': 259.36" +
                "}";
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops", HttpMethod.POST, request,
                Laptop.class);
        Laptop result = response.getBody();
        assertEquals(1L, result.getId());
    }


    @Test
    void testFindAll() {
        ResponseEntity<Laptop[]> response = testRestTemplate.getForEntity("/api/Laptops", Laptop[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<Laptop> Laptops = Arrays.asList(response.getBody());
        System.out.println(Laptops.size());
    }

    @Test
    void testUpdate() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        String json = "{" +
                "'id':'1'," +
                "'brand': 'Asus'," +
                "'model': 'xt1203'," +
                "'price': 259.36" +
                "}";
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops", HttpMethod.POST, request,
                Laptop.class);
        Laptop result = response.getBody();
        assertEquals(1L, result.getId());
    }
}
