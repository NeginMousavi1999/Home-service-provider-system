package ir.maktab.repository;

import ir.maktab.config.SpringConfig;
import ir.maktab.model.members.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Negin Mousavi
 */
public class CustomerRepositoryFindByEmailTest {
    CustomerRepository customerRepository;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        customerRepository = context.getBean(CustomerRepository.class);
    }

    @Test
    void givenExistEmail_WhenFindByEmailCalls_ThenReturnTrueResponse() {
        Customer customer = customerRepository.findByEmail("jack@gmail.com").get();
        assertNotNull(customer);
    }

    @Test
    void givenNotExistEmail_WhenFindByEmailCalls_ThenReturnTrueResponse() {
        Customer customer = customerRepository.findByEmail("something@gmail.com").get();
        assertNull(customer);
    }
}
