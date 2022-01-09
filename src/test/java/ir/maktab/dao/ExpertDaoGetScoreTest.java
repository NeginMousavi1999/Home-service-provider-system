package ir.maktab.dao;

import ir.maktab.config.SpringConfig;
import ir.maktab.model.members.Expert;
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
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        expertDao = context.getBean(ExpertDao.class);
    }

    @Test
    void givenExpert_WhenGetScoreCalls_ThenReturnTrueResponse() {
        Expert expert = expertDao.findByEmail("rachel@gmail.com");
        double score = expertDao.getScore(expert);
        assertEquals(expert.getScore(), score);
    }
}
