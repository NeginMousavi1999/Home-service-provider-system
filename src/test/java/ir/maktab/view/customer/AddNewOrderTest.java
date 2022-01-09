package ir.maktab.view.customer;

import ir.maktab.config.SpringConfig;
import ir.maktab.model.members.Customer;
import ir.maktab.model.order.Address;
import ir.maktab.model.services.SubService;
import ir.maktab.view.CustomerView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Negin Mousavi
 */
public class AddNewOrderTest {
    CustomerView customerView;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        customerView = context.getBean(CustomerView.class);
    }

    @Test
    void addOrderTestWithValidValues_WhenAddNewOrderCalls_ThenReturnTrueResponse() {
        Address orderAddress = new Address();
        orderAddress.setState("address");
        SubService subService = customerView.getSubServiceByName("Kitchen appliances");
        Customer customer = customerView.getCustomerByEmail("jack@gmail.com");
        boolean result = customerView.addNewOrder(subService, orderAddress,
                customer, "description2");
        assertTrue(result);
    }

    @ParameterizedTest
    @CsvSource({"Kitchen appliances, address, jk@gmail.com, description",
            "something, address, jack@gmail.com, description"})
    void addOrderTestWithInvalidValues_WhenAddNewOrderCalls_ThenReturnTrueResponse(String subServiceName, String address
            , String customerUsername, String description) {
        Address orderAddress = new Address();
        orderAddress.setState(address);
        SubService subService = customerView.getSubServiceByName(subServiceName);
        Customer customer = customerView.getCustomerByEmail(customerUsername);
        boolean result = customerView.addNewOrder(subService, orderAddress,
                customer, description);
        assertFalse(result);
    }
}
