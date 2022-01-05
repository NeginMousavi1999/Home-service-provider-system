package view.expert;

import config.ServicesSpringConfig;
import config.ViewSpringConfig;
import model.members.Expert;
import model.order.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.ExpertService;
import service.OrderService;
import util.GenerateDate;
import view.ExpertView;

import java.util.Date;

/**
 * @author Negin Mousavi
 */
public class SendSuggestionTest {
    ExpertView view;
    OrderService orderService;
    ExpertService expertService;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ViewSpringConfig.class);
        AnnotationConfigApplicationContext context2 = new AnnotationConfigApplicationContext(ServicesSpringConfig.class);
        view = (ExpertView) context.getBean("expertView");
        orderService = (OrderService) context2.getBean("orderService");
        expertService = (ExpertService) context2.getBean("expertService");
    }

    @Test
    void test() {
        Order order = orderService.findById(4);
        Expert expert = expertService.findByEmail("rachel@gmail.com");
        Date date = GenerateDate.generateByPattern("yyyy-MM-dd", "2022-01-06");
        view.sendSuggestion(expert, order, 150000, 12, date);
    }
}
