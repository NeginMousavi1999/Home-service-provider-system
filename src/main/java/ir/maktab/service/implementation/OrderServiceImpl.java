package ir.maktab.service.implementation;

import ir.maktab.data.entity.members.Customer;
import ir.maktab.data.entity.order.Order;
import ir.maktab.data.entity.services.SubService;
import ir.maktab.data.enumuration.OrderStatus;
import ir.maktab.data.repository.OrderRepository;
import ir.maktab.exception.HomeServiceException;
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
public class OrderServiceImpl implements OrderService {
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
