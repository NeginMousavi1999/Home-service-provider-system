package dao;

import config.DaoSpringConfig;
import model.members.Customer;
import model.members.Expert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Negin Mousavi
 */
public class ExpertDaoFindByEmail {
    ExpertDao expertDao;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DaoSpringConfig.class);
        expertDao = (ExpertDao) context.getBean("expertDao");
    }

    @Test
    void givenExistEmail_WhenFindByEmailCalls_ThenReturnTrueResponse() {
        Expert expert = expertDao.findByEmail("rachel@gmail.com");
        assertNotNull(expert);
    }

    @Test
    void givenNotExistEmail_WhenFindByEmailCalls_ThenReturnTrueResponse() {
        Expert expert = expertDao.findByEmail("something@gmail.com");
        assertNull(expert);
    }
}
