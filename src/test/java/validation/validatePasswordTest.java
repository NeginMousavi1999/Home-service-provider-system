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
public class validatePasswordTest {
    Validation validation;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        validation = (Validation) context.getBean("validation");
    }

    @ParameterizedTest
    @CsvSource({"123Nwn00", "Jack1w34", "roSee312"})
    void givenValidPassword_WhenValidatePasswordCalls_ThenReturnTrueResponse(String password) {
        boolean result = validation.validatePassword(password);
        assertTrue(result);
    }

    @ParameterizedTest
    @CsvSource({"abc", "Nana123", "helloBye"})
    void givenInvalidPassword_WhenValidatePasswordCalls_ThenExceptionResponseReturn(String password) {
        Exception exception = assertThrows(RuntimeException.class, () -> validation.validatePassword(password));
        assertEquals("the password must be at least 8 character, with a lower case, an upper case and no whitespace", exception.getMessage());
    }
}
