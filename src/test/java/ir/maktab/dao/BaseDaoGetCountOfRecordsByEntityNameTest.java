/*
package ir.maktab.dao;

import ir.maktab.config.SpringConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

*/
/**
 * @author Negin Mousavi
 *//*

public class BaseDaoGetCountOfRecordsByEntityNameTest {
    BaseDao baseDao;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        baseDao = context.getBean(BaseDao.class);
    }

    @ParameterizedTest
    @CsvSource({"Expert, 0", "Comment, 0", "Service, 0", "System_Order, 0"})
    void givenEntityName_WhenGetCountOfRecordsByEntityNameCalls_ThenReturnTrueResponse(String entityName, Long expect) {
        Long result = baseDao.getCountOfRecordsByEntityName(entityName);
        assertEquals(expect, result);
    }
}
*/
