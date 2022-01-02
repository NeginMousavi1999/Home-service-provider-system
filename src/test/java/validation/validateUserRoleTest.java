package validation;

import config.SpringConfig;
import enumuration.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import validation.Validation;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Negin Mousavi
 */
public class validateUserRoleTest {
    Validation validation;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        validation = (Validation) context.getBean("validation");
    }

    @Test
    void givenEqualsRole_WhenValidateUserRoleCalls_ThenReturnTrueResponse() {
        boolean result = validation.validateUserRole(UserRole.CUSTOMER, UserRole.CUSTOMER);
        assertTrue(result);
    }

    @Test
    void givenNotEqualsRole_WhenValidateUserRoleCalls_ThenExceptionResponseReturn() {
        Exception exception = assertThrows(RuntimeException.class, () -> validation.validateUserRole(UserRole.CUSTOMER, UserRole.MANAGER));
        assertEquals("your role is not customer!", exception.getMessage());
    }
}
