package view.customer;

import config.ServicesSpringConfig;
import config.ViewSpringConfig;
import enumuration.OrderStatus;
import model.members.Customer;
import model.members.Expert;
import model.order.Order;
import model.order.Suggestion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.CustomerService;
import service.OrderService;
import view.CustomerView;

import java.util.Set;

/**
 * @author Negin Mousavi
 */
public class PayTest {
    CustomerView customerView;
    CustomerService customerService;
    OrderService orderService;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ViewSpringConfig.class);
        customerView = (CustomerView) context.getBean("customerView");
        AnnotationConfigApplicationContext context2 = new AnnotationConfigApplicationContext(ServicesSpringConfig.class);
        customerService = (CustomerService) context2.getBean("customerService");
        orderService = (OrderService) context2.getBean("orderService");
    }

    @Test
    void test() {
        Customer customer = customerService.findByEmail("jack@gmail.com");
        Set<Order> orders = customerView.getOrdersForPay(customer);
        System.out.println(orders.size());
        for (Order order : orders) {
            customerView.pay(order);
        }
    }
}
