package dao;

import config.DaoSpringConfig;
import enumuration.UserRole;
import enumuration.UserStatus;
import model.members.Expert;
import model.members.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Negin Mousavi
 */
public class ExpertDaoTest {
    ExpertDao expertDao;
    Expert expert;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DaoSpringConfig.class);
        expertDao = (ExpertDao) context.getBean("expertDao");
    }

    @Test
    void givenExpert_WhenCreateCalls_ThenReturnTrueResponse() {
        expert = Expert.builder()
                .firstName("")
                .lastName("")
                .userRole(UserRole.EXPERT)
                .userStatus(UserStatus.WAITING)
                .expertise("")
                .score(0)
                .password("Jack1234")
                .email("jack@gmail.com")
                .credit(0)
                .build();

        Long before = expertDao.getCountOfRecordsByEntityName("Expert");
        expertDao.create(expert);
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
