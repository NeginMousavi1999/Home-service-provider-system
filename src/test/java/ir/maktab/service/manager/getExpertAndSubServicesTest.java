package ir.maktab.service.manager;

import ir.maktab.config.SpringConfig;
import ir.maktab.data.dto.ExpertDto;
import ir.maktab.data.dto.SubServiceDto;
import ir.maktab.service.ManagerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @author Negin Mousavi
 */
public class getExpertAndSubServicesTest {
    ManagerService managerService;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        managerService = context.getBean(ManagerService.class);
    }

    @Test
    void test() {
        Map<ExpertDto, Set<SubServiceDto>> expertAndSubServices = managerService.getExpertAndSubServices(1003);
        Collection<Set<SubServiceDto>> values = expertAndSubServices.values();
        values.forEach(subServiceDtos -> subServiceDtos.stream().map(SubServiceDto::getName).forEach(System.out::println));
    }
}
