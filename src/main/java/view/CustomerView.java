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

/**
 * @author Negin Mousavi
 */
@Data
public class CustomerView {
    private CustomerService customerService;
    private OrderService orderService;
    private ExpertService expertService;
    private SubServiceService subServiceService;

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

    public void showPanel(User user) {

    }

    private SubService getSubService() {
        String name = getSubServiceName();
        SubService subService;
        try {
            subService = subServiceService.findSubServiceByName(name);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            subService = null;
        }
        return subService;
    }

    private String getSubServiceName() {
        return "Kitchen appliances";//TODO
    }

    public void addNewOrder() {
        SubService subService = getSubService();
        if (subService == null)
            return;

        Order order = Order.builder()
                .address(getOrderAddress())
                .customer(getOrderCustomer())
                .description(getOrderDescription())
                .subService(subService)
                .suggestedPrice(getSuggestedPrice())
                .orderStatus(OrderStatus.WAITING_FOR_SPECIALIST_SELECTION)
                .build();
        orderService.saveOrder(order);
    }

    private String getOrderDescription() {
        return "this my order";
    }

    private Customer getOrderCustomer() {
        return customerService.findByEmail("jack@gmail.com");
    }

    private String getOrderAddress() {
        return "address";
    }

    private double getSuggestedPrice() {
        return 120000;
    }
}
