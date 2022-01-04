package view.customer;

import config.ViewSpringConfig;
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
        boolean result = customerView.addNewOrder("Kitchen appliances", "address",
                "jack@gmail.com", "description", 120000);
        assertTrue(result);
    }

    @ParameterizedTest
    @CsvSource({"Kitchen appliances, address, jk@gmail.com, description, 1200000",
            "something, address, jack@gmail.com, description, 1200000"})
    void addOrderTestWithInvalidValues_WhenAddNewOrderCalls_ThenReturnTrueResponse(String subServiceName, String address
            , String customerUsername, String description, double suggestedPrice) {
        boolean result = customerView.addNewOrder(subServiceName, address,
                customerUsername, description, suggestedPrice);
        assertFalse(result);
    }
}
