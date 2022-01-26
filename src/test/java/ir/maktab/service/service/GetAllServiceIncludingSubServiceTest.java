package ir.maktab.service.service;

import ir.maktab.config.SpringConfig;
import ir.maktab.data.dto.ServiceDto;
import ir.maktab.service.ServiceService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Set;

/**
 * @author Negin Mousavi
 */
public class GetAllServiceIncludingSubServiceTest {
    ServiceService serviceService;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        serviceService = context.getBean(ServiceService.class);
    }

    @org.junit.jupiter.api.Test
    void getAllServiceIncludingSubServiceCalls_ThenExceptionResponseReturn() {
       /* List<ServiceDto> list = serviceService.getAllServiceIncludingSubService();
        System.out.println("list size: " + list.size());
        for (ServiceDto serviceDto : list) {
            System.out.println(serviceDto);
            System.out.println("*****");
        }*/
        System.out.println("***************");
        Set<ServiceDto> set = serviceService.getAllServiceIncludingSubService();
        System.out.println("set size: " + set.size());
        for (ServiceDto serviceDto : set) {
            System.out.println(serviceDto);
            System.out.println("*****");
        }
    }
}
