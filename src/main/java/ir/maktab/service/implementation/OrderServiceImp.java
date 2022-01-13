package ir.maktab.service.implementation;

import ir.maktab.enumuration.OrderStatus;
import ir.maktab.exception.HomeServiceException;
import ir.maktab.model.members.Customer;
import ir.maktab.model.order.Order;
import ir.maktab.model.services.SubService;
import ir.maktab.repository.OrderRepository;
import ir.maktab.service.OrderService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author Negin Mousavi
 */
@RequiredArgsConstructor
@Service
@Getter
public class OrderServiceImp implements OrderService {
    private final OrderRepository orderRepository;

    public void update(Order order) {
        orderRepository.save(order);
    }

    public boolean saveOrder(Order order) {
        orderRepository.save(order);
        return true;
    }

    public Order findById(int id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty())
            throw new HomeServiceException("we have not order with this id!");
        return order.get();
    }

    public List<Order> findBySubService(SubService subService) {
        return orderRepository.findBySubService(subService);
    }

    public Set<Order> getOrdersByCustomer(Customer customer) {
        return new HashSet<>(orderRepository.findByCustomer(customer));
    }

    public List<Order> getOrdersByCustomerAndStatus(Customer customer, OrderStatus orderStatus) {
        return orderRepository.findByCustomerAndOrderStatus(customer, orderStatus);
    }
}
