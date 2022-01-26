package ir.maktab.view.expert;

import ir.maktab.config.SpringConfig;
import ir.maktab.view.ExpertView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Negin Mousavi
 */
public class ReturnOrdersWithSameExpertiseExpertTest {
    ExpertView expertView;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        expertView = context.getBean(ExpertView.class);
    }

    @Test
    void giveExistExpert_WhenReturnOrdersWithSameExpertiseExpertCall_ThenReturnTrueResponse() {
        /*Expert expert = expertView.getExpertService().findByEmail("rose@gmail.com");
        List<Order> orderList = expertView.returnOrdersWithSameServiceExpert(expert);
        assertNotNull(orderList);*/
    }
}
