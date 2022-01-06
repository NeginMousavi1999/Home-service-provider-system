package dao;

import config.DaoSpringConfig;
import enumuration.UserRole;
import enumuration.UserStatus;
import model.members.Expert;
import model.members.User;
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
public class ExpertDaoTest {
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
                .firstName("rachel")
                .lastName("rachel")
                .userRole(UserRole.EXPERT)
                .userStatus(UserStatus.WAITING)
                .services(services)
                .score(0)
                .password("Rachel34")
                .email("rachel@gmail.com")
                .credit(0)
                .build();

        Long before = expertDao.getCountOfRecordsByEntityName("Expert");
        expertDao.createExpert(expert);
        Long after = expertDao.getCountOfRecordsByEntityName("Expert");
        assertEquals(before, after - 1);
    }

    @Test
    void givenExpert_WhenDeleteCalls_ThenReturnTrueResponse() { //TODO: it is not completely correct!
        expert = (Expert) expertDao.read(1L);
        Long before = expertDao.getCountOfRecordsByEntityName("Expert");
        expertDao.delete(expert);
        Long after = expertDao.getCountOfRecordsByEntityName("Expert");
        assertEquals(before, after + 1);
    }

    @Test
    void givenExpert_WhenUpdateCalls_ThenReturnTrueResponse() {
        expert = (Expert) expertDao.read(1L);
        expert.setUserStatus(UserStatus.CONFIRMED);
        expertDao.update(expert);
        User updatedExpert = expertDao.read(1L);
        assertEquals(expert, updatedExpert);
    }
}
