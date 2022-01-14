package ir.maktab.data.repository;

import ir.maktab.config.SpringConfig;
import ir.maktab.data.entity.order.Order;
import ir.maktab.data.entity.services.SubService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Negin Mousavi
 */
public class OrderRepositoryFindBySubServiceTest {
    OrderRepository orderRepository;
    SubServiceRepository subServiceRepository;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        orderRepository = context.getBean(OrderRepository.class);
        subServiceRepository = context.getBean(SubServiceRepository.class);
    }

    @Test
    void givenSubServiceAndTableIsEmpty_WhenFindBySubServiceCalls_ThenReturnTrueResponse() {
        SubService subService = subServiceRepository.findByName("Kitchen appliances").get();
        List<Order> orderList = orderRepository.findBySubService(subService).get();
        assertNull(orderList);
    }

    @Test
    void givenSubServiceAndTableIsNotEmpty_WhenFindBySubServiceCalls_ThenReturnTrueResponse() {
        SubService subService = subServiceRepository.findByName("Kitchen appliances").get();
        List<Order> orderList = orderRepository.findBySubService(subService).get();
        assertNotNull(orderList);
    }
}
