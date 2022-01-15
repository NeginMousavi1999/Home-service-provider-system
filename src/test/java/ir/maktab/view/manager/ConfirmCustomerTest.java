package ir.maktab.view.manager;

import ir.maktab.config.SpringConfig;
import ir.maktab.data.entity.members.Customer;
import ir.maktab.data.enumuration.UserStatus;
import ir.maktab.view.ManagerView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Negin Mousavi
 */
public class ConfirmCustomerTest {
    ManagerView managerView;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        managerView = context.getBean(ManagerView.class);
    }

    @Test
    void givenCustomer_WhenConfirmCustomerCalls_ThenReturnTrueResponse() {//todo
 /*       Customer customer = managerView.getCustomerService().findByEmail("jack@gmail.com");
        boolean result = managerView.confirmCustomer(customer);
        Customer confirmedCustomer = managerView.getCustomerService().findByEmail("jack@gmail.com");
        assertTrue(result);
        assertEquals(UserStatus.CONFIRMED, confirmedCustomer.getUserStatus());*/
    }
}
