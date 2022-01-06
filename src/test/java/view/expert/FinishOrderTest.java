package view.expert;

import config.ViewSpringConfig;
import enumuration.OrderStatus;
import model.order.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import view.ExpertView;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * @author Negin Mousavi
 */
public class FinishOrderTest {
    ExpertView view;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ViewSpringConfig.class);
        view = (ExpertView) context.getBean("expertView");
    }

    @Test
    void giveValidOrder_WhenFinishOrderCall_ThenReturnTrueResponse() {
        Order order = view.getOrderService().findById(1);
        view.finishOrder(order);
        order = view.getOrderService().findById(1);
        assertEquals(order.getOrderStatus(), OrderStatus.DONE);
    }

    @Test
    void giveInvalidOrder_WhenFinishOrderCall_ThenReturnTrueResponse() {
        Order order = view.getOrderService().findById(2);
        view.startOrder(order);
        order = view.getOrderService().findById(2);
        assertNotEquals(order.getOrderStatus(), OrderStatus.DONE);
    }
}
