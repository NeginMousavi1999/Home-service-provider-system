package ir.maktab.view.customer;

import ir.maktab.config.SpringConfig;
import ir.maktab.enumuration.OrderStatus;
import ir.maktab.model.members.Customer;
import ir.maktab.model.members.Expert;
import ir.maktab.model.order.Order;
import ir.maktab.model.order.Suggestion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ir.maktab.view.CustomerView;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Negin Mousavi
 */
public class ChooseSuggestionAndExpertTest {
    CustomerView customerView;

    @BeforeEach
    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        customerView = context.getBean(CustomerView.class);
    }

    @Test
    void giveValidInputs_WhenChooseSuggestionsForChoosingExpertCalls_ThenReturnTrueResponse() {
        Customer customer = customerView.getCustomerService().findByEmail("jack@gmail.com");
        List<Order> orders = customerView.returnCustomerOrders(customer);
        List<Suggestion> suggestions = customerView.returnChosenOrderSuggestions(orders, 0);
        Expert expert = customerView.returnChosenExpert(suggestions, 0);
        assertNotNull(expert);
        Order order = customerView.getOrderService().findById(1);
        assertEquals(OrderStatus.WAITING_FOR_THE_SPECIALIST_TO_COME_TO_YOUR_PLACE, order.getOrderStatus());
    }
}
