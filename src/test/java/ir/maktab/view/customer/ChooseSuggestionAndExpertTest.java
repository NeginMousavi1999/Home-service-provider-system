package ir.maktab.view.customer;

import ir.maktab.config.SpringConfig;
import ir.maktab.view.CustomerView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
    void giveValidInputs_WhenChooseSuggestionsForChoosingExpertCalls_ThenReturnTrueResponse() {//todo
        /*Customer customer = customerView.getCustomerService().findByEmail("jack@gmail.com");
        List<Order> orderList = customerView.getOrdersByCustomerAndStatus(customer,
                OrderStatus.WAITING_FOR_SPECIALIST_SELECTION);
        List<Suggestion> suggestions = customerView.returnSortedSuggestionsByChosenOrder(orderList, 0);
        Expert expert = customerView.returnChosenExpert(suggestions, 0);
        assertNotNull(expert);
        Order order = customerView.getOrderService().findById(1);
        assertEquals(OrderStatus.WAITING_FOR_THE_SPECIALIST_TO_COME_TO_YOUR_PLACE, order.getOrderStatus());*/
    }
}
