package ir.maktab.view.expert;

import ir.maktab.config.SpringConfig;
import ir.maktab.view.ExpertView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Negin Mousavi
 */
public class FinishOrderTest {
    ExpertView view;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        view = context.getBean(ExpertView.class);
    }

    @Test
    void giveValidOrder_WhenFinishOrderCall_ThenReturnTrueResponse() {
/*        Order order = view.getOrderService().findById(1);
        view.finishOrder(order);
        order = view.getOrderService().findById(1);
        assertEquals(order.getOrderStatus(), OrderStatus.DONE);*/
    }

    @Test
    void giveInvalidOrder_WhenFinishOrderCall_ThenReturnTrueResponse() {
/*        Order order = view.getOrderService().findById(2);
        view.startOrder(order);
        order = view.getOrderService().findById(2);
        assertNotEquals(order.getOrderStatus(), OrderStatus.DONE);*/
    }
}
