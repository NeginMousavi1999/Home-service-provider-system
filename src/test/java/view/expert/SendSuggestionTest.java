package view.expert;

import config.ViewSpringConfig;
import model.members.Expert;
import model.order.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import util.GenerateDate;
import view.ExpertView;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Negin Mousavi
 */
public class SendSuggestionTest {
    ExpertView view;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ViewSpringConfig.class);
        view = (ExpertView) context.getBean("expertView");
    }

    @Test
    void givenValidInputs_WhenSendSuggestionCalls_ThenReturnTrueResponse() {
        Long before = view.getSuggestionService().getSuggestionDao().getCountOfRecordsByEntityName("Suggestion");
        Order order = view.getOrderService().findById(2);
        Expert expert = view.getExpertService().findByEmail("rachel@gmail.com");
        Date date = GenerateDate.generateByPattern("yyyy-MM-dd", "2022-01-12");
        view.sendSuggestion(expert, order, 150000, 12, date);
        Long after = view.getSuggestionService().getSuggestionDao().getCountOfRecordsByEntityName("Suggestion");
        assertEquals(before, after - 1);
    }
}
