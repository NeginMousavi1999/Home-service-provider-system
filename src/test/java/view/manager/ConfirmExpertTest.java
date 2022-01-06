package view.manager;

import config.ViewSpringConfig;
import enumuration.UserStatus;
import model.members.Expert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import view.ManagerView;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Negin Mousavi
 */
public class ConfirmExpertTest {
    ManagerView managerView;

    @BeforeEach
    public void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ViewSpringConfig.class);
        managerView = (ManagerView) context.getBean("managerView");
    }

    @Test
    void givenCustomer_WhenConfirmExpertCalls_ThenReturnTrueResponse() {
        Expert expert = managerView.getExpertService().findByEmail("rachel@gmail.com");
        boolean result = managerView.confirmExpert(expert);
        Expert confirmedExpert = managerView.getExpertService().findByEmail("rachel@gmail.com");
        assertTrue(result);
        assertEquals(UserStatus.CONFIRMED, confirmedExpert.getUserStatus());
    }
}
