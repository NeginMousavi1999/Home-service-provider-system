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
public class AddNewSubServiceTest {
    ManagerView managerView;

    @BeforeEach
    public void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ViewSpringConfig.class);
        managerView = (ManagerView) context.getBean("managerView");
    }

    @Test
    void givenNotDuplicateNames_WhenAddNewSubServiceCalls_ThenReturnTrueResponse() {
        boolean result = managerView.addNewSubService("Kitchen appliances", "Home Appliances", 1000000
                , "our kitchen service :)");
        assertTrue(result);
    }

    @Test
    void givenDuplicateSubSerName_WhenAddNewServiceCalls_ThenReturnTrueResponse() {
        boolean result = managerView.addNewSubService("Kitchen appliances", "", 0, "");
        assertFalse(result);
    }

    @Test
    void givenNotExistsSerName_WhenAddNewServiceCalls_ThenReturnTrueResponse() {
        boolean result = managerView.addNewSubService("some thing", "some thing", 0, "");
        assertFalse(result);
    }
}
