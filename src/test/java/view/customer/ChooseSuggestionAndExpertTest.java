package view.customer;

import config.SpringConfig;
import enumuration.OrderStatus;
import model.members.Customer;
import model.members.Expert;
import model.order.Order;
import model.order.Suggestion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import view.CustomerView;

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
