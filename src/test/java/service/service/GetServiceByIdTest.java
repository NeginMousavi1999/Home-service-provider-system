package service.service;

import config.SpringConfig;
import model.services.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.ServiceService;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Negin Mousavi
 */
public class GetServiceByIdTest {
    ServiceService serviceService;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        serviceService = context.getBean(ServiceService.class);
    }

    @Test
    void givenNotExistsService_WhenGetServiceByIdCalls_ThenExceptionResponseReturn() {
        Exception exception = assertThrows(RuntimeException.class, () -> serviceService.getServiceById(1));
        assertEquals("service not found!", exception.getMessage());
    }

    @Test
    void givenExistsService_WhenGetServiceByIdCalls_ThenReturnTrueResponse() {
        Service service = serviceService.getServiceById(1);
        assertNotNull(service);
    }
}
