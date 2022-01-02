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
public class validateEmailTest {
    Validation validation;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        validation = (Validation) context.getBean("validation");
    }

    @ParameterizedTest
    @CsvSource({"negin.s.mousavi@gmail.com", "masoud.saghar@gmail.com", "nargesi.SHima@gmail.com"})
    void givenValidEmail_WhenValidateEmailCalls_ThenReturnTrueResponse(String email) {
        boolean result = validation.validateEmail(email);
        assertTrue(result);
    }

    @ParameterizedTest
    @CsvSource({".tmu.hashemi@gmail.com", "tmu.hashemi@gmail.com.", "tmu..hashemi@gmail.com"})
    void givenInvalidEmail_WhenValidateEmailCalls_ThenExceptionResponseReturn(String email) {
        Exception exception = assertThrows(RuntimeException.class, () -> validation.validateEmail(email));
        assertEquals("invalid email!", exception.getMessage());
    }
}
