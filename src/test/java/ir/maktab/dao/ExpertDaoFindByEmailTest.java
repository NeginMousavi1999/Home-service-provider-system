package ir.maktab.dao;

import ir.maktab.config.SpringConfig;
import ir.maktab.model.members.Expert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Negin Mousavi
 */
public class ExpertDaoFindByEmailTest {
    ExpertDao expertDao;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        expertDao = context.getBean(ExpertDao.class);
    }

    @Test
    void givenExistEmail_WhenFindByEmailCalls_ThenReturnTrueResponse() {
        Expert expert = expertDao.findByEmail("rachel@gmail.com").get();
        assertNotNull(expert);
    }

    @Test
    void givenNotExistEmail_WhenFindByEmailCalls_ThenReturnTrueResponse() {
        Expert expert = expertDao.findByEmail("something@gmail.com").get();
        assertNull(expert);
    }
}