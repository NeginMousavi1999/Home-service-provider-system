package view.user;

import config.ViewSpringConfig;
import dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import view.UserView;

import java.util.List;

/**
 * @author Negin Mousavi
 */
public class ShowUsersFilteringTest {
    UserView userView;

    @BeforeEach
    public void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ViewSpringConfig.class);
        userView = (UserView) context.getBean("userView");
    }

    @Test
    void test() {
        List<UserDto> userDtoList = userView.showUsersFiltering();
        userDtoList.forEach(System.out::println);
    }
}
