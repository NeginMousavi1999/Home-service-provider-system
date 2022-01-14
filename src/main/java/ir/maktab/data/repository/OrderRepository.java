package ir.maktab.data.repository;

import ir.maktab.data.entity.members.Customer;
import ir.maktab.data.entity.order.Order;
import ir.maktab.data.entity.services.SubService;
import ir.maktab.data.enumuration.OrderStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Negin Mousavi
 */
@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {

    Optional<Order> findById(int id);

    List<Order> findBySubService(SubService subService);

    List<Order> findByCustomer(Customer customer);

    List<Order> findByCustomerAndOrderStatus(Customer customer, OrderStatus orderStatus);
}
