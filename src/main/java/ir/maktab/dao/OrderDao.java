package ir.maktab.dao;

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
}
