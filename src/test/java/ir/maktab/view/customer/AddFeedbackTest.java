package ir.maktab.view.customer;

import ir.maktab.config.SpringConfig;
import ir.maktab.model.order.Order;
import ir.maktab.view.CustomerView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Negin Mousavi
 */
public class AddFeedbackTest {
    CustomerView customerView;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        customerView = context.getBean(CustomerView.class);
    }

    @Test
    void givenExistOrderId_WhenAddFeedbackCalls_ThenReturnTrueResponse() {
        Long before = customerView.getCommentService().getCommentDao().getCountOfRecordsByEntityName("Comment");
        Order order = customerView.getOrderService().findById(1);
        customerView.addFeedback(order, "this is my comment", 12.5);
        Long after = customerView.getCommentService().getCommentDao().getCountOfRecordsByEntityName("Comment");
        assertEquals(before, after - 1);
    }
}
