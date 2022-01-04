package service.service;

import config.ServicesSpringConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.ServiceService;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Negin Mousavi
 */
public class ValidateNewNameTest {
    ServiceService serviceService;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ServicesSpringConfig.class);
        serviceService = (ServiceService) context.getBean("serviceService");
    }

    @ParameterizedTest
    @CsvSource({"a", "b", "c"})
    void givenNotDuplicateName_WhenValidateNewNameCalls_ThenReturnTrueResponse(String name) {
        boolean result = serviceService.validateNewName(name);
        assertTrue(result);
    }

    @ParameterizedTest
    @CsvSource({"Home Appliances"})
    void givenDuplicateName_WhenValidateNewNameCalls_ThenReturnTrueResponse(String name) {
        Exception exception = assertThrows(RuntimeException.class, () -> serviceService.validateNewName(name));
        assertEquals("duplicate name!", exception.getMessage());
    }
}
