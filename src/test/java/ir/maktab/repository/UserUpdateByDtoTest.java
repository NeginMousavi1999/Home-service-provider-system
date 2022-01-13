package ir.maktab.repository;

import ir.maktab.config.SpringConfig;
import ir.maktab.dto.UserDto;
import ir.maktab.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
        UserDto user = customerService.findByEmailTestForDto("jack@gmail.com");
        user.setFirstName("jackijan");
        customerService.updateTestForDto(user);
    }
}
