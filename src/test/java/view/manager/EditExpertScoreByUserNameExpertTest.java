package view.manager;

import config.ViewSpringConfig;
import model.members.Expert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import view.ManagerView;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Negin Mousavi
 */
public class EditExpertScoreByUserNameExpertTest {
    ManagerView managerView;

    @BeforeEach
    public void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ViewSpringConfig.class);
        managerView = (ManagerView) context.getBean("managerView");
    }

    @Test
    void givenValidUserName_WhenEditExpertScoreByUserNameCalls_ThenReturnTrueResponse() {
        int score = 10;
        boolean result = managerView.editExpertScoreByUsername("rachel@gmail.com", score);
        Expert expert = managerView.getExpertService().findByEmail("rachel@gmail.com");
        assertTrue(result);
        assertEquals(score, expert.getScore());
    }

    @Test
    void givenInValidUserName_WhenEditExpertScoreByUserNameCalls_ThenReturnTrueResponse() {
        boolean result = managerView.editExpertScoreByUsername("", 0);
        assertFalse(result);
    }
}