package service.order;

import config.ServicesSpringConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.OrderService;

/**
 * @author Negin Mousavi
 */
public class SaveOrderTest {
    OrderService orderService;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ServicesSpringConfig.class);
        orderService = (OrderService) context.getBean("orderService");
    }

    @Test
    void givenNotExistsService_WhenGetServiceByIdCalls_ThenExceptionResponseReturn() {

    }

    @Test
    void givenExistsService_WhenGetServiceByIdCalls_ThenReturnTrueResponse() {

    }
}
