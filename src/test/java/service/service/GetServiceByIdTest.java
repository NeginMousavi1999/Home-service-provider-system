package service;

import config.SpringServicesConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Negin Mousavi
 */
public class ServiceServiceTest {
    ServiceService serviceService;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringServicesConfig.class);
        serviceService = (ServiceService) context.getBean("serviceService");
    }

    @Test
    void givenNotExistsService_WhenGetServiceByIdCalls_ThenExceptionResponseReturn() {
        Exception exception = assertThrows(RuntimeException.class, () -> serviceService.getServiceById(1));
        assertEquals("service not found!", exception.getMessage());
    }
}
