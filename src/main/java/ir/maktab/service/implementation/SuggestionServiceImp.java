package ir.maktab.service.implementation;

import ir.maktab.data.enumuration.SuggestionStatus;
import ir.maktab.exception.HomeServiceException;
import ir.maktab.data.entity.members.Expert;
import ir.maktab.data.entity.order.Order;
import ir.maktab.data.entity.order.Suggestion;
import ir.maktab.data.repository.SuggestionRepository;
import ir.maktab.service.SuggestionService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Negin Mousavi
 */
@RequiredArgsConstructor
@Service
@Getter
public class SuggestionServiceImp implements SuggestionService {
    private final SuggestionRepository suggestionRepository;

    public void saveSuggestion(Suggestion suggestion) {
        suggestionRepository.save(suggestion);
    }

    public List<Suggestion> getByStatus(Expert expert, SuggestionStatus suggestionStatus) {
        List<Suggestion> suggestions = suggestionRepository.findBySuggestionStatusAndExpert(suggestionStatus, expert);
        if (suggestions.size() == 0)
            throw new HomeServiceException("no suggestion to show!");
        return suggestions;
    }

    public List<Suggestion> getAllSuggestions(Expert expert) {
        List<Suggestion> suggestions = suggestionRepository.findByExpert(expert);
        if (suggestions.size() == 0)
            throw new HomeServiceException("you have no suggestion!");
        return suggestions;
    }

    public void update(Suggestion suggestion) {
        suggestionRepository.save(suggestion);
    }

    public List<Suggestion> getByOrder(Order order) {
        return suggestionRepository.findByOrder(order);
    }

    public Long getCountOfRecords() {
        return suggestionRepository.count();
    }

    @Override
    public List<Suggestion> getSortedByOrder(Order order) {
        return suggestionRepository.findAll(Sort.by("suggestedPrice", "expert").descending());
    }
}
