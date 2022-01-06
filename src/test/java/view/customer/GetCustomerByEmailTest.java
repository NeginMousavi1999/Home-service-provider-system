package view.customer;

import config.ViewSpringConfig;
import model.members.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import view.CustomerView;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Negin Mousavi
 */
public class GetCustomerByEmailTest {
    CustomerView customerView;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ViewSpringConfig.class);
        customerView = (CustomerView) context.getBean("customerView");
    }

    @ParameterizedTest
    @CsvSource({"jack@gmail.com"})
    void givenValidEmail_WhenGetCustomerByEmailCalls_ThenReturnTrueResponse(String email) {
        Customer result = customerView.getCustomerByEmail(email);
        assertNotNull(result);
    }

    @ParameterizedTest
    @CsvSource({"something"})
    void givenInvalidEmail_WhenGetCustomerByEmailCalls_ThenReturnTrueResponse(String email) {
        Customer result = customerView.getCustomerByEmail(email);
        assertNull(result);
    }
}
