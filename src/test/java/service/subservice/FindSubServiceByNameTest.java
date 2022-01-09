package service.subservice;

import config.SpringConfig;
import model.services.SubService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.SubServiceService;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Negin Mousavi
 */
public class FindSubServiceByNameTest {
    SubServiceService subServiceService;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        subServiceService = context.getBean(SubServiceService.class);
    }

    @ParameterizedTest
    @CsvSource({"Kitchen appliances"})
    void givenValidName_WhenFindSubServiceByNameCalls_ThenReturnTrueResponse(String name) {
        SubService result = subServiceService.findSubServiceByName(name);
        assertNotNull(result);
    }

    @ParameterizedTest
    @CsvSource({"a", "c", "d"})
    void givenInvalidName_WhenFindSubServiceByNameCalls_ThenReturnTrueResponse(String name) {
        Exception exception = assertThrows(RuntimeException.class, () -> subServiceService.findSubServiceByName(name));
        assertEquals("we have n't this sub service!", exception.getMessage());
    }
}
