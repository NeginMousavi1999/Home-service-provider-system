package view;

import enumuration.OrderStatus;
import enumuration.SuggestionStatus;
import enumuration.UserRole;
import enumuration.UserStatus;
import lombok.Data;
import model.members.Customer;
import model.members.Expert;
import model.members.User;
import model.order.Comment;
import model.order.Order;
import model.order.Suggestion;
import model.services.SubService;
import service.*;
import validation.Validation;

import java.util.ArrayList;
import java.util.List;
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
    private CommentService commentService;
    private SuggestionService suggestionService;

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
        double price = order.getFinalPrice();
        Customer customer = order.getCustomer();
        try {
            validation.validateCustomerCredit(customer.getCredit(), price);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return;
        }
        Expert expert = order.getExpert();
        customer.setCredit(customer.getCredit() - price);
        expert.setCredit(expert.getCredit() + price);
        order.setOrderStatus(OrderStatus.PAID);

        customerService.update(customer);
        expertService.update(expert);
        orderService.update(order);
    }

    public List<Order> getOrdersForPay(Customer customer) {
        return customer.getOrders().stream().
                filter(order -> order.getOrderStatus().equals(OrderStatus.DONE)).collect(Collectors.toList());
    }

    public SubService getSubServiceByName(String name) {
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
            validation.validateUserStatus(UserStatus.CONFIRMED, customer.getUserStatus());
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

    public Customer getCustomerByEmail(String email) {
        Customer customer;
        try {
            customer = customerService.findByEmail(email);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return null;
        }
        return customer;
    }

    public Expert returnChosenExpert(List<Suggestion> suggestionList, int index) {
        if (suggestionList == null)
            return null;
        Suggestion suggestion = suggestionList.get(index);
        assert suggestion != null;
        Expert expert = suggestion.getExpert();
        Order order = suggestion.getOrder();
        if (!order.getOrderStatus().equals(OrderStatus.WAITING_FOR_SPECIALIST_SELECTION))
            return null;
        suggestion.setSuggestionStatus(SuggestionStatus.ACCEPTED);
        order.setExpert(expert);
        order.setFinalPrice(suggestion.getSuggestedPrice());
        order.setOrderStatus(OrderStatus.WAITING_FOR_THE_SPECIALIST_TO_COME_TO_YOUR_PLACE);
        order.setToBeDoneDate(suggestion.getStartTime());
        suggestion.setOrder(order);
        suggestionService.update(suggestion);
        return expert;
    }

    public List<Order> returnCustomerOrders(Customer customer) {
        return new ArrayList<>(customer.getOrders());
    }

    public List<Suggestion> returnChosenOrderSuggestions(List<Order> orderList, int index) {
        if (orderList == null)
            return null;
        Order order = orderList.get(index);
        assert order != null;
        if (!order.getOrderStatus().equals(OrderStatus.WAITING_FOR_SPECIALIST_SELECTION))
            return null;
        return new ArrayList<>(order.getSuggestions());
    }

    public void addFeedback(Order order, String customerComment, double score) {
        if (!order.getOrderStatus().equals(OrderStatus.PAID))
            return;
        Expert expert = order.getExpert();
        double oldScore = expertService.getExpertScore(expert);
        expert.setScore((oldScore + score) / 2);
        if (customerComment.length() != 0) {
            Comment comment = Comment.builder()
                    .comment(customerComment)
                    .expert(expert)
                    .customer(order.getCustomer())
                    .order(order)
                    .build();
            commentService.save(comment);
        }
        expertService.update(expert);
    }

    public boolean changePassword(Customer customer, String newPass) {
        customer.setPassword(newPass);
        customerService.update(customer);
        return true;
    }
}
