package view.user;

import config.ViewSpringConfig;
import dto.UserDto;
import model.members.UserViewRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import view.UserView;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Negin Mousavi
 */
public class ReturnUsersWithFilteringTest {
    UserView userView;

    @BeforeEach
    public void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ViewSpringConfig.class);
        userView = (UserView) context.getBean("userView");
    }

    @Test
    void giveValidInputs_WhenReturnUsersWithFilteringCalls_ThenReturnTrueResponse() {

        UserViewRequest request = UserViewRequest.builder()
                .firstName("rachel")
                .lastName("rachel")
                .email("rachel@gmail.com")

/*                .firstName("jack")
                .lastName("jack")
                .email("jack@gmail.com")*/
                //.serviceName("Home Appliances")
//                .userRole(CUSTOMER)
                .build();

        List<UserDto> userDtoList = userView.returnUsersWithFiltering(request);
        assertTrue(userDtoList.size() > 0);
    }
}
