package validation;

import config.ViewSpringConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Negin Mousavi
 */
public class validateCorrectPasswordTest {
    Validation validation;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Validation.class);
        validation = (Validation) context.getBean("validation");
    }

    @ParameterizedTest
    @CsvSource({"1234, 1234", "abc, abc", "abc1234, abc1234"})
    void givenEqualsPasswords_WhenValidateCorrectPasswordCalls_ThenReturnTrueResponse(String oldPass, String newPass) {
        boolean result = validation.validateCorrectPassword(oldPass, newPass);
        assertTrue(result);
    }

    @ParameterizedTest
    @CsvSource({"1234, 123456", "12abc, abc", "abc1234, 1234abc"})
    void givenNotEqualsPasswords_WhenValidateCorrectPasswordCalls_ThenExceptionResponseReturn(String oldPass, String newPass) {
        Exception exception = assertThrows(RuntimeException.class, () -> validation.validateCorrectPassword(oldPass, newPass));
        assertEquals("password is wrong!", exception.getMessage());
    }
}
