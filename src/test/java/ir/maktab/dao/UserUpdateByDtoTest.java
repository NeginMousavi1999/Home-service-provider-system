package ir.maktab.dao;

import ir.maktab.config.SpringConfig;
import ir.maktab.dto.UserDto;
import ir.maktab.model.members.User;
import ir.maktab.service.CustomerService;
import ir.maktab.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Negin Mousavi
 */
public class UserUpdateByDtoTest {
    CustomerService customerService;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        customerService = context.getBean(CustomerService.class);
    }

    @Test
    void test_ThenReturnTrueResponse() {
        UserDto user = customerService.findByEmail2("jack@gmail.com");
        user.setFirstName("jackijan");
        customerService.update2(user);
    }
}
