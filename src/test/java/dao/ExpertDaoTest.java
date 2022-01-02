package dao;

import config.SpringConfig;
import enumuration.UserRole;
import enumuration.UserStatus;
import model.members.Expert;
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
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        expertDao = (ExpertDao) context.getBean("expertDao");
        expert = Expert.builder()
                .firstName("")
                .lastName("")
                .userRole(UserRole.EXPERT)
                .userStatus(UserStatus.WAITING)
                .expertise("")
                .score(0)
                .password("")
                .email("")
                .credit(0)
                .build();
    }

    @Test
    void givenExpert_WhenCreateCalls_ThenReturnTrueResponse() {
        Long before = expertDao.getCountOfRecordsByEntityName("Expert");
        expertDao.create(this.expert);
        Long after = expertDao.getCountOfRecordsByEntityName("Expert");
        assertEquals(before, after - 1);
    }
}
