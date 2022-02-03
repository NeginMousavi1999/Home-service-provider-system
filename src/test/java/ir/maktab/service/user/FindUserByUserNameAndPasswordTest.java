package ir.maktab.service.user;

import ir.maktab.config.SpringConfig;
import ir.maktab.data.dto.LoginDto;
import ir.maktab.data.dto.UserDto;
import ir.maktab.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Negin Mousavi
 */
public class FindUserByUserNameAndPasswordTest {
    UserService userService;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        userService = context.getBean(UserService.class);
    }

    @ParameterizedTest
    @CsvSource({"jack@gmail.com, Jack1234"})
    void givenCorrectUsernamePasswords_WhenFindUserByUserNameAndPasswordCalls_ThenReturnTrueResponse(String email, String password) {
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername(email);
        loginDto.setPassword(password);
        UserDto user = userService.findUserByUserNameAndPassword(loginDto);
        assertNotNull(user);
    }

    @ParameterizedTest
    @CsvSource({"something, something"})
    void givenIncorrectUsernamePasswords_WhenFindUserByUserNameAndPasswordCalls_ThenExceptionResponseReturn(String email, String password) {
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername(email);
        loginDto.setPassword(password);
        Exception exception = assertThrows(RuntimeException.class, () -> userService.findUserByUserNameAndPassword(loginDto));
        assertEquals("username or password is incorrect", exception.getMessage());
    }
}
