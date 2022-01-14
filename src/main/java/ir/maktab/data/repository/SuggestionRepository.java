package ir.maktab.data.repository;

import ir.maktab.data.entity.members.Expert;
import ir.maktab.data.entity.order.Order;
import ir.maktab.data.entity.order.Suggestion;
import ir.maktab.data.enumuration.SuggestionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Negin Mousavi
 */
@Repository
public interface SuggestionRepository extends JpaRepository<Suggestion, Integer> {
    List<Suggestion> findBySuggestionStatusAndExpert(SuggestionStatus suggestionStatus, Expert expert);

    List<Suggestion> findByExpert(Expert expert);

    List<Suggestion> findByOrder(Order order);

    // todo: Optional<List<Suggestion>>
}
