package ir.maktab.service.subservice;

import ir.maktab.config.SpringConfig;
import ir.maktab.data.dto.SubServiceDto;
import ir.maktab.service.SubServiceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
    @CsvSource({"Laundry"})
    void givenValidName_WhenFindSubServiceByNameCalls_ThenReturnTrueResponse(String name) {
        SubServiceDto subServiceByName = subServiceService.findSubServiceByName(name);
        assertNull(subServiceByName);
    }

    @ParameterizedTest
    @CsvSource({"a", "c", "d"})
    void givenInvalidName_WhenFindSubServiceByNameCalls_ThenReturnTrueResponse(String name) {
        Exception exception = assertThrows(RuntimeException.class, () -> subServiceService.findSubServiceByName(name));
        assertEquals("we have n't this sub ir.maktab.service!", exception.getMessage());
    }
}
