package view.expert;

import config.ServicesSpringConfig;
import config.ViewSpringConfig;
import model.members.Expert;
import model.order.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.ExpertService;
import view.ExpertView;

import java.util.List;

/**
 * @author Negin Mousavi
 */
public class ReturnOrdersWithSameExpertiseExpertTest {
    ExpertView expertView;
    ExpertService expertService;

    @BeforeEach
    public void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ViewSpringConfig.class);
        expertView = (ExpertView) context.getBean("expertView");
        AnnotationConfigApplicationContext context2 = new AnnotationConfigApplicationContext(ServicesSpringConfig.class);
        expertService = (ExpertService) context2.getBean("expertService");
    }

    @Test
    void test() {
        Expert expert = expertService.findByEmail("jack2@gmail.com");
        List<Order> orderList = expertView.returnOrdersWithSameExpertiseExpert(expert);
        Order order = expertView.chooseOrderForSendingSuggestion(orderList, 0);
        System.out.println(order.getId());
    }
}
