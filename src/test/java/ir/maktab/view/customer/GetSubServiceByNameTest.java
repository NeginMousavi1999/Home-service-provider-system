package ir.maktab.view.customer;

import ir.maktab.config.SpringConfig;
import ir.maktab.data.entity.services.SubService;
import ir.maktab.view.CustomerView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Negin Mousavi
 */
public class GetSubServiceByNameTest {
    CustomerView customerView;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        customerView = context.getBean(CustomerView.class);
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
