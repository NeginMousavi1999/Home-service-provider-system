package ir.maktab.validation;

import ir.maktab.config.SpringConfig;
import ir.maktab.enumuration.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Negin Mousavi
 */
public class ValidateUserRoleTest {
    Validation validation;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        validation = context.getBean(Validation.class);
    }

    @Test
    void givenEqualsRole_WhenValidateUserRoleCalls_ThenReturnTrueResponse() {
        boolean result = validation.validateUserRole(UserRole.CUSTOMER, UserRole.CUSTOMER);
        assertTrue(result);
    }

    @Test
    void givenNotEqualsRole_WhenValidateUserRoleCalls_ThenExceptionResponseReturn() {
        Exception exception = assertThrows(RuntimeException.class, () -> validation.validateUserRole(UserRole.CUSTOMER, UserRole.EXPERT));
        assertEquals("your role is not customer!", exception.getMessage());
    }
}
