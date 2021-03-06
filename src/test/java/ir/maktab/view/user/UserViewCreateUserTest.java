package ir.maktab.view.user;

import ir.maktab.config.SpringConfig;
import ir.maktab.data.entity.members.User;
import ir.maktab.view.UserView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Negin Mousavi
 */
public class UserViewCreateUserTest {
    UserView view;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        view = context.getBean(UserView.class);
    }

    @ParameterizedTest
    @CsvSource({"jack, jack, jack@gmail.com, jacK0123, EXPERT", "rose, rose, rose@gmail.com, Rose0123, CUSTOMER"})
    void givenInputs_WhenCreateUserCalls_ThenReturnTrueResponse(String firstName, String lastName, String email,
                                                                String password, String role) {
        User user = view.createUser(firstName, lastName, email, password, role, 0, "decorate", "10");
        assertNotNull(user);
    }
}
