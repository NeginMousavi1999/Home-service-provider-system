package ir.maktab.service;

import ir.maktab.data.enumuration.OrderStatus;
import ir.maktab.data.entity.members.Customer;
import ir.maktab.data.entity.order.Order;
import ir.maktab.data.entity.services.SubService;

import java.util.List;
import java.util.Set;

/**
 * @author Negin Mousavi
 */

public interface OrderService {

    void update(Order order);

    boolean saveOrder(Order order);

    Order findById(int id);

    List<Order> findBySubService(SubService subService);

    Set<Order> getOrdersByCustomer(Customer customer);

    List<Order> getOrdersByCustomerAndStatus(Customer customer, OrderStatus orderStatus);
}
