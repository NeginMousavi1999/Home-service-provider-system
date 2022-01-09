package view.expert;

import config.SpringConfig;
import model.members.Expert;
import model.order.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import view.ExpertView;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        Expert expert = expertView.getExpertService().findByEmail("rachel@gmail.com");
        List<Order> orderList = expertView.returnOrdersWithSameServiceExpert(expert);
        assertNotNull(orderList);
    }
}
