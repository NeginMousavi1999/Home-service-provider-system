package ir.maktab.service.service;

import ir.maktab.config.SpringConfig;
import ir.maktab.service.ServiceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        assertEquals("ir.maktab.service not found!", exception.getMessage());
    }

    @Test
    void givenExistsService_WhenGetServiceByIdCalls_ThenReturnTrueResponse() {
/*        Service service = serviceService.getServiceById(1);
        assertNotNull(service);*/
    }
}
