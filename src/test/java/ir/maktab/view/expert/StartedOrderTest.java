package ir.maktab.view.expert;

import ir.maktab.config.SpringConfig;
import ir.maktab.data.enumuration.OrderStatus;
import ir.maktab.data.entity.order.Order;
import ir.maktab.view.ExpertView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * @author Negin Mousavi
 */
public class StartedOrderTest {
    ExpertView view;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        view = context.getBean(ExpertView.class);
    }

    @Test
    void giveValidOrder_WhenStartOrderCall_ThenReturnTrueResponse() {
        Order order = view.getOrderService().findById(1);
        view.startOrder(order);
        order = view.getOrderService().findById(1);
        assertEquals(order.getOrderStatus(), OrderStatus.STARTED);
    }

    @Test
    void giveInvalidOrder_WhenStartOrderCall_ThenReturnTrueResponse() {
        Order order = view.getOrderService().findById(2);
        view.startOrder(order);
        order = view.getOrderService().findById(2);
        assertNotEquals(order.getOrderStatus(), OrderStatus.STARTED);
    }
}
