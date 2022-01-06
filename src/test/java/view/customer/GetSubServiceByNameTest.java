package view.customer;

import config.ViewSpringConfig;
import model.services.SubService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import view.CustomerView;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Negin Mousavi
 */
public class GetSubServiceByNameTest {
    CustomerView customerView;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ViewSpringConfig.class);
        customerView = (CustomerView) context.getBean("customerView");
    }

    @Test
    void givenValidName_WhenGetSubServiceByNameCalls_ThenReturnTrueResponse() {
        SubService result = customerView.getSubServiceByName("Kitchen appliances");
        assertNotNull(result);
    }

    @Test
    void givenInValidName_WhenGetSubServiceByNameCalls_ThenReturnTrueResponse() {
        SubService result = customerView.getSubServiceByName("something");
        assertNull(result);
    }
}
