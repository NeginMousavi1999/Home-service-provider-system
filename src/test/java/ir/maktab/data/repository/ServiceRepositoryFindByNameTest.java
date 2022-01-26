package ir.maktab.data.repository;

import ir.maktab.config.SpringConfig;
import ir.maktab.data.entity.services.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Negin Mousavi
 */
public class ServiceRepositoryFindByNameTest {
    ServiceRepository serviceRepository;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        serviceRepository = context.getBean(ServiceRepository.class);
    }

    @Test
    void givenExistName_WhenFindByNameCalls_ThenReturnTrueResponse() {
        String serviceName = "Home Appliances";
        Service service = serviceRepository.findByName(serviceName).get();
        assertNotNull(service);
    }

    @Test
    void givenNotExistName_WhenFindByNameCalls_ThenReturnTrueResponse() {
        String serviceName = "something";
        Service service = serviceRepository.findByName(serviceName).get();
        assertNull(service);
    }
}
