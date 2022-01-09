package ir.maktab.view.customer;

import ir.maktab.config.SpringConfig;
import ir.maktab.model.members.Customer;
import ir.maktab.model.order.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ir.maktab.view.CustomerView;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Negin Mousavi
 */
public class GetOrdersForPayTest {
    CustomerView customerView;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        customerView = context.getBean(CustomerView.class);
    }

    @Test
    void givenExistOrderId_WhenAddFeedbackCalls_ThenReturnTrueResponse() {
        Customer customer = customerView.getCustomerByEmail("jack@gmail.com");
        List<Order> ordersForPay = customerView.getOrdersForPay(customer);
        assertNotNull(ordersForPay);
    }
}
