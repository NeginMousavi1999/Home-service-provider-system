package view.customer;

import config.ServicesSpringConfig;
import config.ViewSpringConfig;
import model.members.Customer;
import model.order.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.CommentService;
import service.CustomerService;
import service.OrderService;
import view.CustomerView;

import java.util.Set;

/**
 * @author Negin Mousavi
 */
public class AddFeedbackTest {
    CustomerView customerView;
    CustomerService customerService;
    OrderService orderService;
    CommentService commentService;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ViewSpringConfig.class);
        customerView = (CustomerView) context.getBean("customerView");
        AnnotationConfigApplicationContext context2 = new AnnotationConfigApplicationContext(ServicesSpringConfig.class);
        customerService = (CustomerService) context2.getBean("customerService");
        commentService = (CommentService) context2.getBean("commentService");
        orderService = (OrderService) context2.getBean("orderService");
    }

    @Test
    void test() {
        Order order = orderService.findById(1);
        customerView.addFeedback(order, "this is my comment", 12.5);
    }
}
