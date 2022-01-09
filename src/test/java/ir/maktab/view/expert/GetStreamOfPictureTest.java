package ir.maktab.view.expert;

import ir.maktab.config.SpringConfig;
import ir.maktab.view.ExpertView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Negin Mousavi
 */
public class GetStreamOfPictureTest {
    ExpertView view;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        view = context.getBean(ExpertView.class);
    }

    @ParameterizedTest
    @CsvSource({"static-pictures/2.png", "static-pictures/6.png", "static-pictures/8.png"})
    void givenValidFileName_WhenGetStreamOfPictureCalls_ThenReturnTrueResponse(String fileName) {
        InputStream stream = view.getStreamOfPicture(fileName);
        assertNotNull(stream);
    }

    @ParameterizedTest
    @CsvSource({"static-pictures/abc", "static-pictures/55.png", "static-pictures/12.png"})
    void givenInvalidFileName_WhenGetStreamOfPictureCalls_ThenExceptionResponseReturn(String fileName) {
        Exception exception = assertThrows(RuntimeException.class, () -> view.getStreamOfPicture(fileName));
        assertEquals("unable to get resources", exception.getMessage());
    }
}
