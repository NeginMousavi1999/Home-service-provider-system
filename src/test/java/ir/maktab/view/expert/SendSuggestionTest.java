package ir.maktab.view.expert;

import ir.maktab.config.SpringConfig;
import ir.maktab.model.members.Expert;
import ir.maktab.model.order.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ir.maktab.util.GenerateDate;
import ir.maktab.view.ExpertView;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Negin Mousavi
 */
public class SendSuggestionTest {
    ExpertView view;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        view = context.getBean(ExpertView.class);
    }

    @Test
    void givenValidInputs_WhenSendSuggestionCalls_ThenReturnTrueResponse() {
        Long before = view.getSuggestionService().getSuggestionDao().getCountOfRecordsByEntityName("Suggestion");
        Order order = view.getOrderService().findById(1);
        Expert expert = view.getExpertService().findByEmail("rose@gmail.com");
        Date date = GenerateDate.generateByPattern("yyyy-MM-dd", "2022-01-12");
        view.sendSuggestion(expert, order, 150000, 12, date);
        Long after = view.getSuggestionService().getSuggestionDao().getCountOfRecordsByEntityName("Suggestion");
        assertEquals(before, after - 1);
    }
}
