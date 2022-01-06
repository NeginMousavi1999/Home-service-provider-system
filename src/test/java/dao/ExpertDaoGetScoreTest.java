package dao;

import config.DaoSpringConfig;
import model.members.Expert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Negin Mousavi
 */
public class ExpertDaoGetScoreTest {
    ExpertDao expertDao;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DaoSpringConfig.class);
        expertDao = (ExpertDao) context.getBean("expertDao");
    }

    @Test
    void givenExpert_WhenGetScoreCalls_ThenReturnTrueResponse() {
        Expert expert = expertDao.findByEmail("rachel@gmail.com");
        double score = expertDao.getScore(expert);
        assertEquals(expert.getScore(), score);
    }
}
