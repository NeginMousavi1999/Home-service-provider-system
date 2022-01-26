package ir.maktab.view.expert;

import ir.maktab.config.SpringConfig;
import ir.maktab.view.ExpertView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
  /*      Long before = view.getSuggestionService().getCountOfRecords();
        Order order = view.getOrderService().findById(1);
        Expert expert = view.getExpertService().findByEmail("rose@gmail.com");
        Date date = GenerateDate.generateByPattern("yyyy-MM-dd", "2022-01-12");
        view.sendSuggestion(expert, order, 90, 12, date);
        long after = view.getSuggestionService().getCountOfRecords();
        assertEquals(before, after - 1);*/
    }
}
