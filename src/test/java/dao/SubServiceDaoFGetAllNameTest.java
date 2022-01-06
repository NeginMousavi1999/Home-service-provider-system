package dao;

import config.DaoSpringConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Negin Mousavi
 */
public class SubServiceDaoFGetAllNameTest {
    SubServiceDao subServiceDao;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DaoSpringConfig.class);
        subServiceDao = (SubServiceDao) context.getBean("subServiceDao");
    }

    @Test
    void callGetAllName_WhenTableIsNotEmpty_ThenReturnTrueResponse() {
        List<String> allName = subServiceDao.getAllName();
        assertTrue(allName.size() > 0);
    }
}
