package ir.maktab.data.repository;

import ir.maktab.config.SpringConfig;
import ir.maktab.data.entity.members.Expert;
import ir.maktab.data.entity.order.Order;
import ir.maktab.data.entity.services.SubService;
import ir.maktab.data.enumuration.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

/**
 * @author Negin Mousavi
 */
public class OrderRepositoryFindReadyOrdersForExpertTest {
    private OrderRepository orderRepository;
    private ExpertRepository expertRepository;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        orderRepository = context.getBean(OrderRepository.class);
        expertRepository = context.getBean(ExpertRepository.class);
    }

    @Test
    void test() {
        Optional<Expert> optionalExpert = expertRepository.findByEmail("jack@gmail.com");
        Expert expert = optionalExpert.get();
        Optional<List<SubService>> subServices = expertRepository.customeGetSubServiceByExpertId(expert.getId());
        Optional<List<Order>> optionalResult = orderRepository.findReadyOrdersForExpert(OrderStatus.FEEDEDBACK, new HashSet<>(subServices.get()));
        for (Order order : optionalResult.get()) {
            System.out.println(order.getId());
        }
    }
}
