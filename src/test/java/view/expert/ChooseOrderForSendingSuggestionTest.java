package view.expert;

import config.ViewSpringConfig;
import model.order.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import view.ExpertView;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Negin Mousavi
 */
public class ChooseOrderForSendingSuggestionTest {
    ExpertView view;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ViewSpringConfig.class);
        view = (ExpertView) context.getBean("expertView");
    }

    @Test
    void giveValidOrder_WhenChooseOrderForSendingSuggestionCall_ThenReturnTrueResponse() {
        Order order = view.getOrderService().findById(1);
        Order founded = view.chooseOrderForSendingSuggestion(List.of(order), 0);
        assertEquals(founded, order);
    }
}
