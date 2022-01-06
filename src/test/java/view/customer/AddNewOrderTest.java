package view.customer;

import config.ViewSpringConfig;
import model.members.Customer;
import model.services.SubService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import view.CustomerView;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Negin Mousavi
 */
public class AddNewOrderTest {
    CustomerView customerView;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ViewSpringConfig.class);
        customerView = (CustomerView) context.getBean("customerView");
    }

    @Test
    void addOrderTestWithValidValues_WhenAddNewOrderCalls_ThenReturnTrueResponse() {
        SubService subService = customerView.getSubServiceByName("Kitchen appliances");
        Customer customer = customerView.getCustomerByEmail("jack@gmail.com");
        boolean result = customerView.addNewOrder(subService, "address2",
                customer, "description2", 1330000);
        assertTrue(result);
    }

    @ParameterizedTest
    @CsvSource({"Kitchen appliances, address, jk@gmail.com, description, 1200000",
            "something, address, jack@gmail.com, description, 1200000",
            "Kitchen appliances, address, jack@gmail.com, description, 120"})
    void addOrderTestWithInvalidValues_WhenAddNewOrderCalls_ThenReturnTrueResponse(String subServiceName, String address
            , String customerUsername, String description, double suggestedPrice) {
        SubService subService = customerView.getSubServiceByName(subServiceName);
        Customer customer = customerView.getCustomerByEmail(customerUsername);
        boolean result = customerView.addNewOrder(subService, address,
                customer, description, suggestedPrice);
        assertFalse(result);
    }
}
