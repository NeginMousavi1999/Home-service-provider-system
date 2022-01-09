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
public class AddNewServiceTest {
    ManagerView managerView;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        managerView = context.getBean(ManagerView.class);
    }

    @Test
    void givenNotDuplicateName_WhenAddNewServiceCalls_ThenReturnTrueResponse() {
        boolean result = managerView.addNewService("Home Appliances");
        assertTrue(result);
    }

    @Test
    void givenDuplicateName_WhenAddNewServiceCalls_ThenReturnTrueResponse() {
        boolean result = managerView.addNewService("Home Appliances");
        assertFalse(result);
    }
}
