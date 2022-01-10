package ir.maktab.view.customer;

import ir.maktab.config.SpringConfig;
import ir.maktab.enumuration.OrderStatus;
import ir.maktab.model.members.Customer;
import ir.maktab.model.members.Expert;
import ir.maktab.model.order.Order;
import ir.maktab.model.order.Suggestion;
import ir.maktab.view.CustomerView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
        List<Order> orders = customerView.returnOrdersByCustomer(customer);
        List<Order> orderList =customerView.returnWatingForSpecialistSelectionOrders(orders);
        List<Suggestion> suggestions = customerView.returnSuggestionsByChosenOrder(orderList, 0);
        Expert expert = customerView.returnChosenExpert(suggestions, 5);
        assertNotNull(expert);
        Order order = customerView.getOrderService().findById(1);
        assertEquals(OrderStatus.WAITING_FOR_THE_SPECIALIST_TO_COME_TO_YOUR_PLACE, order.getOrderStatus());
    }
}
