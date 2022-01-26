package ir.maktab.view.user;

import ir.maktab.config.SpringConfig;
import ir.maktab.view.UserView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Negin Mousavi
 */
public class LoginTest {
    UserView userView;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        userView = context.getBean(UserView.class);
    }

    @ParameterizedTest
    @CsvSource({"jack@gmail.com, Jack1234"})
    void givenCorrectUsernamePasswords_WhenFindUserByUserNameAndPasswordCalls_ThenReturnTrueResponse(String username, String password) {
        boolean result = userView.login(username, password);
        assertTrue(result);
    }
}
