package ir.maktab.repository;

import ir.maktab.config.SpringConfig;
import ir.maktab.model.services.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Negin Mousavi
 */
public class ServiceRepositoryFGetAllNameTest {
    ServiceRepository serviceRepository;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        serviceRepository = context.getBean(ServiceRepository.class);
    }

    @Test
    void callGetAllName_WhenTableIsNotEmpty_ThenReturnTrueResponse() {
        List<String> allName = serviceRepository.findAll().stream().map(Service::getName).collect(Collectors.toList());
        assertTrue(allName.size() > 0);
    }
}
