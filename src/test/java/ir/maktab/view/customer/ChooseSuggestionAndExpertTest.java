package ir.maktab.view.customer;

import ir.maktab.config.SpringConfig;
import ir.maktab.data.entity.members.Customer;
import ir.maktab.data.entity.members.Expert;
import ir.maktab.data.entity.order.Order;
import ir.maktab.data.entity.order.Suggestion;
import ir.maktab.data.enumuration.OrderStatus;
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
        List<Order> orderList = customerView.getOrdersByCustomerAndStatus(customer,
                OrderStatus.WAITING_FOR_SPECIALIST_SELECTION);
        List<Suggestion> suggestions = customerView.returnSortedSuggestionsByChosenOrder(orderList, 0);
        Expert expert = customerView.returnChosenExpert(suggestions, 0);
        assertNotNull(expert);
        Order order = customerView.getOrderService().findById(1);
        assertEquals(OrderStatus.WAITING_FOR_THE_SPECIALIST_TO_COME_TO_YOUR_PLACE, order.getOrderStatus());
    }
}
