package ir.maktab.data.repository;

import ir.maktab.data.dto.OrdersHistoryDto;
import ir.maktab.data.entity.members.Customer;
import ir.maktab.data.entity.members.Expert;
import ir.maktab.data.entity.order.Order;
import ir.maktab.data.entity.services.SubService;
import ir.maktab.data.enumuration.OrderStatus;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Negin Mousavi
 */
@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {

    static Specification<Order> selectByConditions(OrdersHistoryDto request) {///TODO
        return (Specification<Order>) (root, cq, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (request.getFromDate() != null && request.getFromDate().length() != 0)
                predicateList.add(cb.equal(root.get("firstName"), request.getFromDate()));
            if (request.getToDate() != null && request.getToDate().length() != 0)
                predicateList.add(cb.equal(root.get("lastName"), request.getToDate()));
            if (request.getStatus() != null && request.getStatus().length() != 0)
                predicateList.add(cb.equal(root.get("email"), request.getStatus()));
            if (request.getService() != null && request.getService().length() != 0)
                predicateList.add(cb.equal(root.get("userRole"), request.getService()));
            return cb.and(predicateList.toArray(new Predicate[0]));
        };
    }

    Optional<List<Order>> findBySubService(SubService subService);

    Optional<List<Order>> findByCustomer(Customer customer);

    Optional<List<Order>> findByCustomerAndOrderStatus(Customer customer, OrderStatus orderStatus);

    @Transactional
    @Modifying
    @Query(value = "update System_Order o set o.orderStatus=:orderStatus where o.id=:id")
    void updateStatus(@Param("id") int id, @Param("orderStatus") OrderStatus orderStatus);

    @Query(value = "from System_Order o where o.orderStatus=:orderStatus1 or o.orderStatus=:orderStatus2")
    Optional<List<Order>> findByOrderStatusAndOrderStatus(@Param("orderStatus1") OrderStatus orderStatus1,
                                                          @Param("orderStatus2") OrderStatus orderStatus2);

    Optional<List<Order>> findByExpertAndOrderStatus(Expert expert, OrderStatus orderStatus);
}
