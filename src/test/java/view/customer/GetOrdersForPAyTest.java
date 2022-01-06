package view.customer;

import config.ViewSpringConfig;
import model.members.Customer;
import model.order.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import view.CustomerView;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Negin Mousavi
 */
public class GetOrdersForPAyTest {
    CustomerView customerView;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ViewSpringConfig.class);
        customerView = (CustomerView) context.getBean("customerView");
    }

    @Test
    void givenExistOrderId_WhenAddFeedbackCalls_ThenReturnTrueResponse() {
        Customer customer = customerView.getCustomerByEmail("jack@gmail.com");
        List<Order> ordersForPay = customerView.getOrdersForPay(customer);
        assertNotNull(ordersForPay);
    }
}
