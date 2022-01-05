package view.expert;

import config.ServicesSpringConfig;
import config.ViewSpringConfig;
import model.order.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.OrderService;
import view.ExpertView;

/**
 * @author Negin Mousavi
 */
public class StartedOrderTest {
    ExpertView view;
    OrderService orderService;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ViewSpringConfig.class);
        AnnotationConfigApplicationContext context2 = new AnnotationConfigApplicationContext(ServicesSpringConfig.class);
        view = (ExpertView) context.getBean("expertView");
        orderService = (OrderService) context2.getBean("orderService");
    }

    @Test
    void test() {
        Order order = orderService.findById(1);
        view.startOrder(order);
    }
}
