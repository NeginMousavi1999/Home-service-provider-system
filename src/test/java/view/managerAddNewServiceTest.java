package view;

import config.ViewSpringConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Negin Mousavi
 */
public class managerAddNewServiceTest {
    ManagerView managerView;

    @BeforeEach
    public void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ViewSpringConfig.class);
        managerView = (ManagerView) context.getBean("managerView");
    }

    @Test
    void givenNotDuplicateName_WhenAddNewServiceCalls_ThenReturnTrueResponse() {
        boolean result = managerView.addNewService();
        assertTrue(result);
    }

    @Test
    void givenDuplicateName_WhenAddNewServiceCalls_ThenExceptionResponseReturn() {
        //TODO
    }
}
