package ir.maktab.service;

import ir.maktab.data.entity.members.Expert;
import ir.maktab.data.entity.order.Order;
import ir.maktab.data.entity.order.Suggestion;
import ir.maktab.data.enumuration.SuggestionStatus;

import java.util.List;

/**
 * @author Negin Mousavi
 */

public interface SuggestionService {


    void saveSuggestion(Suggestion suggestion);

    List<Suggestion> getByStatus(Expert expert, SuggestionStatus suggestionStatus);

    List<Suggestion> getAllSuggestions(Expert expert);

    void update(Suggestion suggestion);

    List<Suggestion> getByOrder(Order order);

    Long getCountOfRecords();

    List<Suggestion> getSortedByOrder(Order order);
}
