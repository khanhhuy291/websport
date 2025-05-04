package com.example.websport.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.example.websport.model.Bill;
import com.example.websport.model.Booking;
import com.example.websport.model.ChildCourts;
import com.example.websport.model.Court;
import com.example.websport.model.Product;
import com.example.websport.model.User;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PaginationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void testBillPagination() {
        // Test basic pagination
        ResponseEntity<Page<Bill>> response = restTemplate.exchange(
                getRootUrl() + "/api/bills?page=0&size=5",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Page<Bill>>() {
                });

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getContent().size() <= 5);
        assertTrue(response.getBody().getTotalElements() >= 0);
        assertTrue(response.getBody().getTotalPages() >= 0);

        // Test pagination with user ID filter
        response = restTemplate.exchange(
                getRootUrl() + "/api/bills/user/1?page=0&size=5",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Page<Bill>>() {
                });

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertNotNull(response.getBody());
    }

    @Test
    public void testUserPagination() {
        ResponseEntity<Page<User>> response = restTemplate.exchange(
                getRootUrl() + "/api/users?page=0&size=5",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Page<User>>() {
                });

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getContent().size() <= 5);
    }

    @Test
    public void testProductPagination() {
        ResponseEntity<Page<Product>> response = restTemplate.exchange(
                getRootUrl() + "/api/products?page=0&size=5",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Page<Product>>() {
                });

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getContent().size() <= 5);
    }

    @Test
    public void testCourtPagination() {
        ResponseEntity<Page<Court>> response = restTemplate.exchange(
                getRootUrl() + "/api/courts?page=0&size=5",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Page<Court>>() {
                });

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getContent().size() <= 5);
    }

    @Test
    public void testChildCourtsPagination() {
        ResponseEntity<Page<ChildCourts>> response = restTemplate.exchange(
                getRootUrl() + "/api/childcourts?page=0&size=5",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Page<ChildCourts>>() {
                });

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getContent().size() <= 5);

        // Test with courtsId filter
        response = restTemplate.exchange(
                getRootUrl() + "/api/childcourts?courtsId=1&page=0&size=5",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Page<ChildCourts>>() {
                });

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertNotNull(response.getBody());
    }

    @Test
    public void testBookingPagination() {
        ResponseEntity<Page<Booking>> response = restTemplate.exchange(
                getRootUrl() + "/api/bookings?page=0&size=5",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Page<Booking>>() {
                });

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getContent().size() <= 5);

        // Test with user ID filter
        response = restTemplate.exchange(
                getRootUrl() + "/api/bookings/user/1?page=0&size=5",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Page<Booking>>() {
                });

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertNotNull(response.getBody());
    }
}