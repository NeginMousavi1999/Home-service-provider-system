package ir.maktab.data.repository;

import ir.maktab.config.SpringConfig;
import ir.maktab.data.enumuration.UserRole;
import ir.maktab.data.enumuration.UserStatus;
import ir.maktab.data.entity.members.Expert;
import ir.maktab.data.entity.services.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Negin Mousavi
 */
public class ExpertRepositoryCreateTest {
    ExpertRepository expertRepository;
    Expert expert;
    ServiceRepository serviceRepository;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        expertRepository = context.getBean(ExpertRepository.class);
        serviceRepository = context.getBean(ServiceRepository.class);
    }

    @Test
    void givenExpert_WhenCreateCalls_ThenReturnTrueResponse() {
        String serviceName = "Home Appliances";
        Service service = serviceRepository.findByName(serviceName).get();
        Set<Service> services = new HashSet<>();
        services.add(service);
        expert = Expert.builder()
                .firstName("rose")
                .lastName("rose")
                .userRole(UserRole.EXPERT)
                .userStatus(UserStatus.WAITING)
                .services(services)
                .score(0)
                .password("Rose1234")
                .email("rose@gmail.com")
                .credit(0)
                .build();

        Long before = expertRepository.count();
        expertRepository.save(expert);
        long after = expertRepository.count();
        assertEquals(before, after - 1);
    }
}
