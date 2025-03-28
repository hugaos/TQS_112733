package tqs.lab6_1;

import tqs.lab6_1.entities.Customer;
import tqs.lab6_1.repository.CustomerRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.util.List;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) 
class CustomerIntegrationTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15")
            .withDatabaseName("test-db")
            .withUsername("testuser")
            .withPassword("testpass");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        // registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
    }

    @Autowired
    private CustomerRepository customerRepository;

    
    static Long storedCustomerId;
    @Test
    @Order(1)
    void InitialDataLoadedByFlywaytest() {
        List<Customer> customers = customerRepository.findAll();
        System.out.println(customers.size());
        assertEquals(3, customers.size());
    }

    @Test
    @Order(2)
    void InsertCustomertest() {
        Customer customer = new Customer();
        customer.setName("Hugo");
        customer.setEmail("hugosousa@ua.pt");

        Customer saved = customerRepository.save(customer);
        storedCustomerId = saved.getId();

        assertNotNull(storedCustomerId);
        System.out.println("Stored customer id: " + storedCustomerId);
    }

    @Test
    @Order(3)
    void RetrieveCustomertest() {
        Optional<Customer> result = customerRepository.findById(storedCustomerId);

        assertTrue(result.isPresent());
        assertEquals("Hugo", result.get().getName());
        assertEquals("hugosousa@ua.pt", result.get().getEmail());
        System.out.println("Retrieved customer id: " + result.get().getId());
    }

    @Test
    @Order(4)
    void UpdateCustomertest() {
        Optional<Customer> optionalCustomer = customerRepository.findById(storedCustomerId);
        assertTrue(optionalCustomer.isPresent());

        Customer customer = optionalCustomer.get();
        customer.setName("Hugo novo");
        customer.setEmail("hugonew@ua.pt");
        customerRepository.save(customer);

        Customer updated = customerRepository.findById(storedCustomerId).get();
        assertEquals("Hugo novo", updated.getName());
        assertEquals("hugonew@ua.pt", updated.getEmail());
        System.out.println("Updated customer id: " + updated.getId());
    }

    @Test
    @Order(5)
    void DeleteCustomertest() {
        customerRepository.deleteById(storedCustomerId);
        Optional<Customer> result = customerRepository.findById(storedCustomerId);
        assertFalse(result.isPresent());
    }
}
