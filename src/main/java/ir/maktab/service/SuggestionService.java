package ir.maktab.service;

import ir.maktab.data.dto.ExpertDto;
import ir.maktab.data.dto.OrderDto;
import ir.maktab.data.dto.SuggestionDto;
import ir.maktab.data.enumuration.SuggestionStatus;

import java.util.List;
import java.util.Set;

/**
 * @author Negin Mousavi
 */

public interface SuggestionService {

    void saveSuggestion(SuggestionDto suggestion);

    List<SuggestionDto> getByStatus(ExpertDto expert, SuggestionStatus suggestionStatus);

    List<SuggestionDto> getAllSuggestions(ExpertDto expert);

    void update(SuggestionDto suggestion);

    Set<SuggestionDto> getByOrder(OrderDto order);

    Long getCountOfRecords();

    List<SuggestionDto> getSortedByOrder(OrderDto order);

    void chooseSuggestion(int suggestionIdentity, List<SuggestionDto> suggestions);
}
