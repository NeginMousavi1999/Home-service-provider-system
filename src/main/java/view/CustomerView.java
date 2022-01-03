package view;

import enumuration.OrderStatus;
import enumuration.UserRole;
import enumuration.UserStatus;
import lombok.Data;
import model.members.Customer;
import model.members.Expert;
import model.members.User;
import model.order.Order;
import service.CustomerService;
import service.ExpertService;
import service.OrderService;

/**
 * @author Negin Mousavi
 */
@Data
public class CustomerView {
    CustomerService customerService;
    OrderService orderService;
    ExpertService expertService;

    public User createCustomer(User customer) {
        double credit = getCredit();
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

    private double getCredit() {
        return 0;
    }

    public void pay() {
        Order order = null;
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

    public void showPanel(User user) {

    }
}
