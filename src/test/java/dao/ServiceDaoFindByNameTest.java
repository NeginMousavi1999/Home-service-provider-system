package dao;

import config.DaoSpringConfig;
import model.services.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Negin Mousavi
 */
public class ServiceDaoFindByNameTest {
    ServiceDao serviceDao;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DaoSpringConfig.class);
        serviceDao = (ServiceDao) context.getBean("serviceDao");
    }

    @Test
    void givenExistName_WhenFindByNameCalls_ThenReturnTrueResponse() {
        String serviceName = "Home Appliances";
        Service service = serviceDao.findByName(serviceName);
        assertNotNull(service);
    }

    @Test
    void givenNotExistName_WhenFindByNameCalls_ThenReturnTrueResponse() {
        String serviceName = "something";
        Service service = serviceDao.findByName(serviceName);
        assertNull(service);
    }
}
