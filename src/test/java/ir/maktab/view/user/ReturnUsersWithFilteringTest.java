package ir.maktab.view.user;

import ir.maktab.config.SpringConfig;
import ir.maktab.dto.UserDto;
import ir.maktab.dto.UserRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ir.maktab.view.UserView;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Negin Mousavi
 */
public class ReturnUsersWithFilteringTest {
    UserView userView;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        userView = context.getBean(UserView.class);
    }

    @Test
    void giveValidInputs_WhenReturnUsersWithFilteringCalls_ThenReturnTrueResponse() {

        UserRequestDto request = UserRequestDto.builder()
                .firstName("rose")
                .lastName("rose")
                .email("rose@gmail.com")
               // .userRole(UserRole.EXPERT)
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
