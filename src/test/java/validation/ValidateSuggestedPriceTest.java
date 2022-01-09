package validation;

import config.SpringConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Negin Mousavi
 */
public class ValidateSuggestedPriceTest {
    Validation validation;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        validation = context.getBean(Validation.class);
    }

    @ParameterizedTest
    @CsvSource({"120.00, 100", "150000, 150000"})
    void givenValidPrice_WhenValidateSuggestedPriceCalls_ThenReturnTrueResponse(double suggestion, double base) {
        boolean result = validation.validateSuggestedPrice(suggestion, base);
        assertTrue(result);
    }

    @ParameterizedTest
    @CsvSource({"90.00, 100", "100, 150000"})
    void givenInvalidPrice_WhenValidateSuggestedPriceCalls_ThenExceptionResponseReturn(double suggestion, double base) {
        Exception exception = assertThrows(RuntimeException.class, () -> validation.validateSuggestedPrice(suggestion, base));
        assertEquals("suggested price is less than base price!", exception.getMessage());
    }
}
