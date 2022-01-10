package ir.maktab.dao;

import ir.maktab.enumuration.OrderStatus;
import ir.maktab.model.members.Customer;
import ir.maktab.model.order.Order;
import ir.maktab.model.services.SubService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Negin Mousavi
 */
@Repository
public interface OrderDao extends JpaRepository<Order, Integer> {

    Optional<Order> findById(int id);

    List<Order> findBySubService(SubService subService);

    List<Order> findByCustomer(Customer customer);

    List<Order> findByCustomerAndOrderStatus(Customer customer, OrderStatus orderStatus);
}
