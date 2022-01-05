package view;

import enumuration.OrderStatus;
import enumuration.UserRole;
import enumuration.UserStatus;
import lombok.Data;
import model.members.Customer;
import model.members.Expert;
import model.members.User;
import model.order.Order;
import model.order.Suggestion;
import model.services.SubService;
import service.CustomerService;
import service.ExpertService;
import service.OrderService;
import service.SubServiceService;
import validation.Validation;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Negin Mousavi
 */
@Data
public class CustomerView {
    private CustomerService customerService;
    private OrderService orderService;
    private ExpertService expertService;
    private SubServiceService subServiceService;
    private Validation validation;

    public User createCustomer(User customer, double credit) {
        customer = Customer.builder()
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .password(customer.getPassword())
                .credit(credit)
                .userStatus(UserStatus.WAITING)
                .userRole(UserRole.CUSTOMER)
                .build();

        customerService.save(customer);
        return customer;
    }

    public void showPanel(User user) {

    }

    public void pay(Order order) {
        if (!order.getOrderStatus().equals(OrderStatus.DONE))
            return;
        Customer customer = order.getCustomer();
        Expert expert = order.getExpert();
        double price = order.getFinalPrice();
        customer.setCredit(customer.getCredit() - price);
        expert.setCredit(expert.getCredit() + price);
        order.setOrderStatus(OrderStatus.PAID);

        customerService.updateCredit(customer);
        expertService.updateCredit(expert);
        orderService.updateStatus(order);
    }

    public Set<Order> getOrdersForPay(Customer customer) {
        return customer.getOrders().stream().
                filter(order -> order.getOrderStatus().equals(OrderStatus.DONE)).collect(Collectors.toSet());
    }

    public SubService getSubService(String name) {
        SubService subService;
        try {
            subService = subServiceService.findSubServiceByName(name);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            subService = null;
        }
        return subService;
    }

    public boolean addNewOrder(SubService subService, String address, Customer customer, String description,
                               double suggestedPrice) {
        if (subService == null || customer == null)
            return false;

        try {
            validation.validateSuggestedPrice(suggestedPrice, subService.getCost());
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return false;
        }

        Order order = Order.builder()
                .address(address)
                .customer(customer)
                .description(description)
                .subService(subService)
                .suggestedPrice(suggestedPrice)
                .orderStatus(OrderStatus.NEW)
                .build();
        customer.getOrders().add(order);
        return orderService.saveOrder(order);
    }

    public Customer getOrderCustomer(String email) {
        Customer customer;
        try {
            customer = customerService.findByEmail(email);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return null;
        }
        return customer;
    }

    public Expert chooseSuggestionsForChoosingExpert(Set<Suggestion> suggestionList, int id) {
        Suggestion suggestion = null;
        for (Suggestion s : suggestionList) {
            if (s.getId() == id)
                suggestion = s;
        }
        assert suggestion != null;
        Expert expert = suggestion.getExpert();
        Order order = suggestion.getOrder();
        if (!order.getOrderStatus().equals(OrderStatus.WAITING_FOR_SPECIALIST_SELECTION))
            return null;
        order.setExpert(expert);
        order.setFinalPrice(suggestion.getSuggestedPrice());
        order.setOrderStatus(OrderStatus.WAITING_FOR_THE_SPECIALIST_TO_COME_TO_YOUR_PLACE);
        order.setToBeDoneDate(suggestion.getStartTime());
        orderService.updateStatus(order);
        return expert;
    }

    public Set<Order> returnCustomerOrders(Customer customer) {
        return customer.getOrders();
    }

    public Set<Suggestion> chooseOrderForShowingSuggestions(Set<Order> orderList, int id) {
        Order order = null;
        for (Order o : orderList) {
            if (o.getId() == id)
                order = o;
        }
        assert order != null;
        if (!order.getOrderStatus().equals(OrderStatus.WAITING_FOR_SPECIALIST_SELECTION))
            return null;
        return order.getSuggestions();
    }
}
