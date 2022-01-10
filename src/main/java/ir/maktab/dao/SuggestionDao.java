package ir.maktab.dao;

import ir.maktab.enumuration.SuggestionStatus;
import ir.maktab.model.members.Expert;
import ir.maktab.model.order.Order;
import ir.maktab.model.order.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Negin Mousavi
 */
@Repository
public interface SuggestionDao extends JpaRepository<Suggestion, Integer> {
    List<Suggestion> findBySuggestionStatusAndExpert(SuggestionStatus suggestionStatus, Expert expert);

    List<Suggestion> findByExpert(Expert expert);

    List<Suggestion> findByOrder(Order order);

}
