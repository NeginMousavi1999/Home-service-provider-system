package validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Negin Mousavi
 */
public class ValidateCustomerCreditTest {
    Validation validation;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Validation.class);
        validation = (Validation) context.getBean("validation");
    }

    @ParameterizedTest
    @CsvSource({"120.00, 100", "150000, 150000"})
    void givenValidCredit_WhenValidateCustomerCreditCalls_ThenReturnTrueResponse(double credit, double price) {
        boolean result = validation.validateCustomerCredit(credit, price);
        assertTrue(result);
    }

    @ParameterizedTest
    @CsvSource({"90.00, 100", "100, 150000"})
    void givenInvalidCredit_WhenValidateCustomerCreditCalls_ThenExceptionResponseReturn(double credit, double price) {
        Exception exception = assertThrows(RuntimeException.class, () -> validation.validateCustomerCredit(credit, price));
        assertEquals("you have not enough credit to pay!", exception.getMessage());
    }
}
