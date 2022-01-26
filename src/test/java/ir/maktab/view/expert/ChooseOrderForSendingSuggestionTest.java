package ir.maktab.view.expert;

import ir.maktab.config.SpringConfig;
import ir.maktab.view.ExpertView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Negin Mousavi
 */
public class ChooseOrderForSendingSuggestionTest {
    ExpertView view;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        view = context.getBean(ExpertView.class);
    }

    @Test
    void giveValidOrder_WhenChooseOrderForSendingSuggestionCall_ThenReturnTrueResponse() {
/*        Order order = view.getOrderService().findById(1);
        Order founded = view.chooseOrderForSendingSuggestion(List.of(order), 0);
        assertEquals(founded, order);*/
    }
}
