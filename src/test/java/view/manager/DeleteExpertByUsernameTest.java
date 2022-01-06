package view.manager;

import config.ViewSpringConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import view.ManagerView;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Negin Mousavi
 */
public class DeleteExpertByUsernameTest {
    ManagerView managerView;

    @BeforeEach
    public void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ViewSpringConfig.class);
        managerView = (ManagerView) context.getBean("managerView");
    }

    @Test
    void givenValidUserNameNotForeignKeyWhenDeleteExpertByUsernameCallsThenReturnTrueResponse() {
        boolean result = managerView.deleteExpertByUsername("rose@gmail.com");
        assertTrue(result);
    }

    @Test
    void givenInValidUserName_WhenDeleteExpertByUsernameCalls_ThenReturnTrueResponse() {
        boolean result = managerView.deleteExpertByUsername("something");
        assertFalse(result);
    }
}
