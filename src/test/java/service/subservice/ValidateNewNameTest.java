package service.subservice;

import config.ServicesSpringConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.SubServiceService;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Negin Mousavi
 */
public class ValidateNewNameTest {
    SubServiceService subServiceService;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ServicesSpringConfig.class);
        subServiceService = (SubServiceService) context.getBean("subServiceService");
    }

    @ParameterizedTest
    @CsvSource({"a", "b", "c"})
    void givenNotDuplicateName_WhenValidateNewNameCalls_ThenReturnTrueResponse(String name) {
        boolean result = subServiceService.validateNewName(name);
        assertTrue(result);
    }

    @ParameterizedTest
    @CsvSource({"", "", ""})
    void givenDuplicateName_WhenValidateNewNameCalls_ThenReturnTrueResponse(String name) {
        Exception exception = assertThrows(RuntimeException.class, () -> subServiceService.validateNewName(name));
        assertEquals("duplicate name!", exception.getMessage());
    }
}
