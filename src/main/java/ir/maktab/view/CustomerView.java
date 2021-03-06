package ir.maktab.view;

import ir.maktab.data.entity.members.Customer;
import ir.maktab.data.entity.members.Expert;
import ir.maktab.data.entity.members.User;
import ir.maktab.data.entity.order.Address;
import ir.maktab.data.entity.order.Comment;
import ir.maktab.data.entity.order.Order;
import ir.maktab.data.entity.order.Suggestion;
import ir.maktab.data.entity.services.SubService;
import ir.maktab.data.enumuration.OrderStatus;
import ir.maktab.data.enumuration.SuggestionStatus;
import ir.maktab.data.enumuration.UserRole;
import ir.maktab.data.enumuration.UserStatus;
import ir.maktab.service.*;
import ir.maktab.validation.Validation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Negin Mousavi
 */
@RequiredArgsConstructor
@Component
@Getter
public class CustomerView {
    private final CustomerService customerService;
    private final OrderService orderService;
    private final ExpertService expertService;
    private final SubServiceService subServiceService;
    private final Validation validation;
    private final CommentService commentService;
    private final SuggestionService suggestionService;

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

        /*try {
            customerService.save((Customer) customer);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return null;
        }*/
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

//        customerService.update(customer);
//        expertService.update(expert);
//        orderService.update(order);
    }

    public SubService getSubServiceByName(String name) {
        SubService subService = null;
        /*try {
            subService = subServiceService.findSubServiceByName(name);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            subService = null;
        }*/
        return subService;
    }

    public boolean addNewOrder(SubService subService, Address address, Customer customer, String description) {
        if (subService == null || customer == null)
            return false;

        try {
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
                .orderStatus(OrderStatus.NEW)
                .build();
        return /*orderService.saveOrder(order)*/true;
    }

    public Customer getCustomerByEmail(String email) {
        Customer customer = null;
        /*try {
            customer = customerService.findByEmail(email);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return null;
        }*/
        return customer;
    }

    public Expert returnChosenExpert(List<Suggestion> suggestionList, int index) {
        if (suggestionList == null)
            return null;

        Suggestion suggestion = null;
        for (int i = 0; i < suggestionList.size(); i++) {
            if (i == index) {
                suggestion = suggestionList.get(index);
                suggestion.setSuggestionStatus(SuggestionStatus.ACCEPTED);
                continue;
            }
            suggestionList.get(index).setSuggestionStatus(SuggestionStatus.REJECTED);
        }
        assert suggestion != null;
        Expert expert = suggestion.getExpert();
        Order order = suggestion.getOrder();
        if (!order.getOrderStatus().equals(OrderStatus.WAITING_FOR_SPECIALIST_SELECTION)) {
            return null;
        }
        order.setExpert(expert);
        order.setFinalPrice(suggestion.getSuggestedPrice());
        order.setOrderStatus(OrderStatus.WAITING_FOR_THE_SPECIALIST_TO_COME_TO_YOUR_PLACE);
        order.setToBeDoneDate(suggestion.getStartTime());
        order.setSuggestions(new HashSet<>(suggestionList));
        suggestion.setOrder(order);
//        orderService.update(order);
//        suggestionService.update(suggestion);
        return expert;
    }

    public List<Order> returnOrdersByCustomer(Customer customer) {
//        Set<Order> orders = orderService.getOrdersByCustomer(customer);
        return new ArrayList<>(/*orders*/);
    }

    public List<Suggestion> returnSortedSuggestionsByChosenOrder(List<Order> orderList, int index) {
        if (orderList == null)
            return null;
        Order order = orderList.get(index);
        assert order != null;
        return /*suggestionService.getSortedByOrder(order)*/null;
    }

    public void addFeedback(Order order, String customerComment, double score) {
        if (!order.getOrderStatus().equals(OrderStatus.PAID))
            return;
        Expert expert = order.getExpert();
        double oldScore = expert.getScore();
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
//        expertService.update(expert);
    }

    public boolean changePassword(Customer customer, String newPass) {
        customer.setPassword(newPass);
//        customerService.update(customer);
        return true;
    }

    public List<Order> returnWatingForSpecialistSelectionOrders(List<Order> orders) {
        return orders.stream().filter(order -> order.getOrderStatus().equals(OrderStatus.WAITING_FOR_SPECIALIST_SELECTION))
                .collect(Collectors.toList());
    }

/*    public List<Order> getOrdersByCustomerAndStatus(Customer customer, OrderStatus orderStatus) {
        return orderService.getOrdersByCustomerAndStatus(customer, orderStatus);
    }*/
}
