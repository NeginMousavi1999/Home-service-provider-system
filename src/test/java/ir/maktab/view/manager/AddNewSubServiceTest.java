package ir.maktab.view.manager;

import ir.maktab.config.SpringConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ir.maktab.view.ManagerView;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Negin Mousavi
 */
public class AddNewSubServiceTest {
    ManagerView managerView;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        managerView = context.getBean(ManagerView.class);
    }

    @Test
    void givenNotDuplicateNames_WhenAddNewSubServiceCalls_ThenReturnTrueResponse() {
        boolean result = managerView.addNewSubService("Kitchen Appliances", "Home Appliances", 1000000
                , "our kitchen ir.maktab.service :)");
        assertTrue(result);
    }

    @Test
    void givenDuplicateSubSerName_WhenAddNewServiceCalls_ThenReturnTrueResponse() {
        boolean result = managerView.addNewSubService("Kitchen Appliances", "", 0, "");
        assertFalse(result);
    }

    @Test
    void givenNotExistsSerName_WhenAddNewServiceCalls_ThenReturnTrueResponse() {
        boolean result = managerView.addNewSubService("something", "something", 0, "");
        assertFalse(result);
    }
}
