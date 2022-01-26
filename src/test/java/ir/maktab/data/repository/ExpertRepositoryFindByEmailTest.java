package ir.maktab.data.repository;

import ir.maktab.config.SpringConfig;
import ir.maktab.data.entity.members.Expert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Negin Mousavi
 */
public class ExpertRepositoryFindByEmailTest {
    ExpertRepository expertRepository;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        expertRepository = context.getBean(ExpertRepository.class);
    }

    @Test
    void givenExistEmail_WhenFindByEmailCalls_ThenReturnTrueResponse() {
        Expert expert = expertRepository.findByEmail("rachel@gmail.com").get();
        assertNotNull(expert);
    }

    @Test
    void givenNotExistEmail_WhenFindByEmailCalls_ThenReturnTrueResponse() {
        Expert expert = expertRepository.findByEmail("something@gmail.com").get();
        assertNull(expert);
    }
}
