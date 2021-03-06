package ir.maktab.view.manager;

import ir.maktab.config.SpringConfig;
import ir.maktab.view.ManagerView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Negin Mousavi
 */
public class ConfirmExpertTest {
    ManagerView managerView;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        managerView = context.getBean(ManagerView.class);
    }

    @Test
    void givenCustomer_WhenConfirmExpertCalls_ThenReturnTrueResponse() {
/*        Expert expert = managerView.getExpertService().findByEmail("rose@gmail.com");
        boolean result = managerView.confirmExpert(expert);
        Expert confirmedExpert = managerView.getExpertService().findByEmail("rose@gmail.com");
        assertTrue(result);
        assertEquals(UserStatus.CONFIRMED, confirmedExpert.getUserStatus());*/
    }
}
