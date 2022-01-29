package ir.maktab.data.repository;

import ir.maktab.data.entity.members.Expert;
import ir.maktab.data.entity.order.Feedback;
import ir.maktab.data.entity.order.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Negin Mousavi
 */
@Repository
public interface FeedbackRepository extends CrudRepository<Feedback, Integer> {
    Optional<Feedback> findByExpertAndOrder(Expert expert, Order order);
}
