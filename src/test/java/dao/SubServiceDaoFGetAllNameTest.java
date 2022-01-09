package dao;

import config.SpringConfig;
import model.services.SubService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Negin Mousavi
 */
public class SubServiceDaoFGetAllNameTest {
    SubServiceDao subServiceDao;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        subServiceDao = context.getBean(SubServiceDao.class);
    }

    @Test
    void callGetAllName_WhenTableIsNotEmpty_ThenReturnTrueResponse() {
        List<String> allName = subServiceDao.returnAll().stream().map(SubService::getName).collect(Collectors.toList());
        assertTrue(allName.size() > 0);
    }
}
