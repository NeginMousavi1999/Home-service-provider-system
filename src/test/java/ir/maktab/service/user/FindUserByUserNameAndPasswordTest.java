package ir.maktab.service.user;

import ir.maktab.config.SpringConfig;
import ir.maktab.model.members.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ir.maktab.service.UserService;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Negin Mousavi
 */
public class FindUserByUserNameAndPasswordTest {
    UserService userService;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        userService = context.getBean(UserService.class);
    }

    @ParameterizedTest
    @CsvSource({"jack@gmail.com, Jack1234"})
    void givenCorrectUsernamePasswords_WhenFindUserByUserNameAndPasswordCalls_ThenReturnTrueResponse(String email, String password) {
        User user = userService.findUserByUserNameAndPassword(email, password);
        assertNotNull(user);
    }

    @ParameterizedTest
    @CsvSource({"something, something"})
    void givenIncorrectUsernamePasswords_WhenFindUserByUserNameAndPasswordCalls_ThenExceptionResponseReturn(String email, String password) {
        Exception exception = assertThrows(RuntimeException.class, () -> userService.findUserByUserNameAndPassword(email, password));
        assertEquals("username or password is incorrect", exception.getMessage());
    }
}
