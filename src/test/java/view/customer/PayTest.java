package view.customer;

import config.ViewSpringConfig;
import enumuration.OrderStatus;
import model.members.Customer;
import model.order.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import view.CustomerView;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Negin Mousavi
 */
public class PayTest {
    CustomerView customerView;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ViewSpringConfig.class);
        customerView = (CustomerView) context.getBean("customerView");
    }

    @Test
    void givenExistCustomerAndValidOrder_WhenPayCalls_ThenReturnTrueResponse() {
        Customer customer = customerView.getCustomerService().findByEmail("jack@gmail.com");
        List<Order> orders = customerView.getOrdersForPay(customer);
        customerView.pay(orders.get(0));
        Order order = customerView.getOrderService().findById(1);
        assertEquals(OrderStatus.PAID, order.getOrderStatus());
    }
}
