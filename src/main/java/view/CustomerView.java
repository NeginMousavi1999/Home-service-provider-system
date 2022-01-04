package view;

import enumuration.OrderStatus;
import enumuration.UserRole;
import enumuration.UserStatus;
import lombok.Data;
import model.members.Customer;
import model.members.Expert;
import model.members.User;
import model.order.Order;
import model.services.SubService;
import service.CustomerService;
import service.ExpertService;
import service.OrderService;
import service.SubServiceService;
import validation.Validation;

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

    public void pay() {
        Order order = null;//TODO
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

    private SubService getSubService(String name) {
        SubService subService;
        try {
            subService = subServiceService.findSubServiceByName(name);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            subService = null;
        }
        return subService;
    }

    public boolean addNewOrder(String subServiceName, String address, String customerUsername, String description,
                            double suggestedPrice) {
        SubService subService = getSubService(subServiceName);
        Customer customer = getOrderCustomer(customerUsername);
        if (subService == null || customer == null)
            return false;

        if (!validation.validateSuggestedPrice(suggestedPrice, subService.getCost()))
            return false;

        Order order = Order.builder()
                .address(address)
                .customer(customer)
                .description(description)
                .subService(subService)
                .suggestedPrice(suggestedPrice)
                .orderStatus(OrderStatus.WAITING_FOR_SPECIALIST_SELECTION)
                .build();
        return orderService.saveOrder(order);
    }

    private Customer getOrderCustomer(String email) {
        Customer customer;
        try {
            customer = customerService.findByEmail(email);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return null;
        }
        return customer;
    }
}
