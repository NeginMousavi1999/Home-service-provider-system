package view.manager;

import config.ViewSpringConfig;
import enumuration.UserStatus;
import model.members.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import view.ManagerView;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Negin Mousavi
 */
public class ConfirmCustomerTest {
    ManagerView managerView;

    @BeforeEach
    public void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ViewSpringConfig.class);
        managerView = (ManagerView) context.getBean("managerView");
    }

    @Test
    void givenCustomer_WhenConfirmCustomerCalls_ThenReturnTrueResponse() {
        Customer customer = managerView.getCustomerService().findByEmail("jack@gmail.com");
        boolean result = managerView.confirmCustomer(customer);
        Customer confirmedCustomer = managerView.getCustomerService().findByEmail("jack@gmail.com");
        assertTrue(result);
        assertEquals(UserStatus.CONFIRMED, confirmedCustomer.getUserStatus());
    }
}
