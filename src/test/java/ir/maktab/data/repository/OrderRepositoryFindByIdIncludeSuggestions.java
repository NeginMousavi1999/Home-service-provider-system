package ir.maktab.data.repository;

import ir.maktab.config.SpringConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Negin Mousavi
 */
public class OrderRepositoryFindByIdIncludeSuggestions {
    OrderRepository orderRepository;
    SubServiceRepository subServiceRepository;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        orderRepository = context.getBean(OrderRepository.class);
        subServiceRepository = context.getBean(SubServiceRepository.class);
    }

    @Test
    void test() {


    }

}
