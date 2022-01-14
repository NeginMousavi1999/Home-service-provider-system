package ir.maktab.view.customer;

import ir.maktab.config.SpringConfig;
import ir.maktab.data.enumuration.UserRole;
import ir.maktab.data.enumuration.UserStatus;
import ir.maktab.data.entity.members.User;
import ir.maktab.view.CustomerView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Negin Mousavi
 */
public class CreateCustomerTest {
    CustomerView customerView;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        customerView = context.getBean(CustomerView.class);
    }

    @Test
    void givenCustomer_WhenCreateCalls_ThenReturnTrueResponse() {
        User user = User.builder()
                .firstName("jack3")
                .lastName("jack3")
                .userRole(UserRole.CUSTOMER)
                .password("Jack1234")
                .email("jack3@gmail.com")
                .credit(1000000)
                .userStatus(UserStatus.NEW)
                .build();
        User customer = customerView.createCustomer(user, 1000000);
        assertNotNull(customer);
    }

    @Test
    void givenDuplicateEmailCustomer_WhenCreateCalls_ThenReturnTrueResponse() {
        User user = User.builder()
                .firstName("jack2")
                .lastName("jack2")
                .userRole(UserRole.CUSTOMER)
                .password("Jack1234")
                .email("jack2@gmail.com")
                .credit(1000000)
                .userStatus(UserStatus.NEW)
                .build();
        User customer = customerView.createCustomer(user, 1000000);
        assertNull(customer);
    }
}
