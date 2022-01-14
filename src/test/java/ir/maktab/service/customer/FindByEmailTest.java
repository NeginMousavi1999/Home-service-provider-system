package ir.maktab.service.customer;

import ir.maktab.config.SpringConfig;
import ir.maktab.data.entity.members.Customer;
import ir.maktab.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Negin Mousavi
 */
public class FindByEmailTest {
    CustomerService customerService;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        customerService = context.getBean(CustomerService.class);
    }

    @ParameterizedTest
    @CsvSource({"jack@gmail.com"})
    void givenValidEmail_WhenFindSubServiceByNameCalls_ThenReturnTrueResponse(String email) {
        Customer result = customerService.findByEmail(email);
        assertNotNull(result);
    }

    @ParameterizedTest
    @CsvSource({"a", "c", "d"})
    void givenInvalidEmail_WhenFindSubServiceByNameCalls_ThenReturnTrueResponse(String email) {
        Exception exception = assertThrows(RuntimeException.class, () -> customerService.findByEmail(email));
        assertEquals("we have not customer with this email", exception.getMessage());
    }
}
