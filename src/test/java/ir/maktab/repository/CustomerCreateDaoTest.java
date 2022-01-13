package ir.maktab.repository;

import ir.maktab.config.SpringConfig;
import ir.maktab.enumuration.UserRole;
import ir.maktab.enumuration.UserStatus;
import ir.maktab.model.members.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Negin Mousavi
 */
public class CustomerCreateDaoTest {
    CustomerRepository customerRepository;
    Customer customer;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        customerRepository = context.getBean(CustomerRepository.class);
    }


    @Test
    void givenCustomer_WhenCreateCalls_ThenReturnTrueResponse() {
        customer = Customer.builder()
                .firstName("jack")
                .lastName("jack")
                .userRole(UserRole.CUSTOMER)
                .userStatus(UserStatus.WAITING)
                .password("Jack1234")
                .email("jack@gmail.com")
                .credit(1000000)
                .build();

        Long before = customerRepository.count();
        customerRepository.save(customer);
        long after = customerRepository.count();
        assertEquals(before, after - 1);
    }

    @Test
    void givenDuplicateEmailCustomer_WhenCreateCalls_ThenReturnTrueResponse() {
        customer = Customer.builder()
                .firstName("jack2")
                .lastName("jack2")
                .userRole(UserRole.CUSTOMER)
                .userStatus(UserStatus.WAITING)
                .password("Jack1234")
                .email("jack2@gmail.com")
                .credit(1000000)
                .build();

        Long before = customerRepository.count();
        customerRepository.save(customer);
        long after = customerRepository.count();
        assertEquals(before, after - 1);
    }
}
