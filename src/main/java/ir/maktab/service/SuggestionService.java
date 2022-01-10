package ir.maktab.service;

import ir.maktab.enumuration.SuggestionStatus;
import ir.maktab.model.members.Expert;
import ir.maktab.model.order.Order;
import ir.maktab.model.order.Suggestion;

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
}
