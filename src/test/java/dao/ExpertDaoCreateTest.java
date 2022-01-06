package dao;

import config.DaoSpringConfig;
import enumuration.UserRole;
import enumuration.UserStatus;
import model.members.Expert;
import model.services.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Negin Mousavi
 */
public class ExpertDaoCreateTest {
    ExpertDao expertDao;
    Expert expert;
    ServiceDao serviceDao;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DaoSpringConfig.class);
        expertDao = (ExpertDao) context.getBean("expertDao");
        serviceDao = (ServiceDao) context.getBean("serviceDao");
    }

    @Test
    void givenExpert_WhenCreateCalls_ThenReturnTrueResponse() {
        String serviceName = "Home Appliances";
        Service service = serviceDao.findByName(serviceName);
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

        Long before = expertDao.getCountOfRecordsByEntityName("Expert");
        expertDao.createExpert(expert);
        Long after = expertDao.getCountOfRecordsByEntityName("Expert");
        assertEquals(before, after - 1);
    }
}
