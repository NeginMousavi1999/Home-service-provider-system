package ir.maktab.dao;

import ir.maktab.config.SpringConfig;
import ir.maktab.model.order.Order;
import ir.maktab.model.services.SubService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Negin Mousavi
 */
public class OrderDaoFindBySubServiceTest {
    OrderDao orderDao;
    SubServiceDao subServiceDao;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        orderDao = context.getBean(OrderDao.class);
        subServiceDao = context.getBean(SubServiceDao.class);
    }

    @Test
    void givenSubServiceAndTableIsEmpty_WhenFindBySubServiceCalls_ThenReturnTrueResponse() {
        SubService subService = subServiceDao.findByName("Kitchen appliances");
        List<Order> orderList = orderDao.findBySubService(subService);
        assertNull(orderList);
    }

    @Test
    void givenSubServiceAndTableIsNotEmpty_WhenFindBySubServiceCalls_ThenReturnTrueResponse() {
        SubService subService = subServiceDao.findByName("Kitchen appliances");
        List<Order> orderList = orderDao.findBySubService(subService);
        assertNotNull(orderList);
    }
}
