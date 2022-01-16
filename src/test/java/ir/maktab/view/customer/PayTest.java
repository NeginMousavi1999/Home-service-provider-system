package ir.maktab.view.customer;

import ir.maktab.config.SpringConfig;
import ir.maktab.view.CustomerView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Negin Mousavi
 */
public class PayTest {
    CustomerView customerView;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        customerView = context.getBean(CustomerView.class);
    }

    @Test
    void givenExistCustomerAndValidOrder_WhenPayCalls_ThenReturnTrueResponse() {//todo
/*        Customer customer = customerView.getCustomerService().findByEmail("jack@gmail.com");
        List<Order> orders = customerView.getOrdersByCustomerAndStatus(customer, OrderStatus.DONE);
        customerView.pay(orders.get(0));
        Order order = customerView.getOrderService().findById(4);
        assertEquals(OrderStatus.PAID, order.getOrderStatus());*/
    }
}
