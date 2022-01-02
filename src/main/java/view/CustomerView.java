package view;

import enumuration.OrderStatus;
import lombok.Data;
import model.members.Customer;
import model.members.Expert;
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
}
