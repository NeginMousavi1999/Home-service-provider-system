package view.user;

import config.ViewSpringConfig;
import model.members.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import view.UserView;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Negin Mousavi
 */
public class ChangeUserPasswordTest {
    UserView userView;

    @BeforeEach
    public void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ViewSpringConfig.class);
        userView = (UserView) context.getBean("userView");
    }

    @ParameterizedTest
    @CsvSource({"jack@gmail.com, Jack1234, jAcj1234"})
    void givenValidInputs_WhenChangeUserPasswordCalls_ThenReturnTrueResponse(String username,
                                                                             String oldPass,
                                                                             String newPass) {
        Customer customer = userView.getCustomerView().getCustomerByEmail(username);
        boolean result = userView.changePassword(customer, oldPass, newPass);
        assertTrue(result);
    }

    @ParameterizedTest
    @CsvSource({"jack@gmail.com, Jack1234, jAcj1234", "something@gmail.com, jAcj1234, Jack1234",
            "jack@gmail.com, jAcj1234, d5"})
    void givenInvalidInputs_WhenChangeUserPasswordCalls_ThenReturnTrueResponse(String username,
                                                                               String oldPass,
                                                                               String newPass) {
        Customer customer = userView.getCustomerView().getCustomerByEmail(username);
        boolean result = userView.changePassword(customer, oldPass, newPass);
        assertFalse(result);
    }
}
