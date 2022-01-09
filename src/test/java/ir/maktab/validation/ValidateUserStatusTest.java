package ir.maktab.validation;

import ir.maktab.config.SpringConfig;
import ir.maktab.enumuration.UserStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Negin Mousavi
 */
public class ValidateUserStatusTest {
    Validation validation;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        validation = context.getBean(Validation.class);
    }

    @Test
    void givenEqualsStatus_WhenValidateUserStatusCalls_ThenReturnTrueResponse() {
        boolean result = validation.validateUserStatus(UserStatus.NEW, UserStatus.NEW);
        assertTrue(result);
    }

    @Test
    void givenNotEqualsStatus_WhenValidateUserStatusCalls_ThenExceptionResponseReturn() {
        Exception exception = assertThrows(RuntimeException.class, () -> validation.validateUserStatus(UserStatus.NEW,
                UserStatus.CONFIRMED));
        assertEquals("your status is not new!", exception.getMessage());
    }
}
