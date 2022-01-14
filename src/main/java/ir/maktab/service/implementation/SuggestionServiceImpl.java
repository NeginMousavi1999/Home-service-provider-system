package ir.maktab.service.implementation;

import ir.maktab.data.entity.members.Expert;
import ir.maktab.data.entity.order.Order;
import ir.maktab.data.entity.order.Suggestion;
import ir.maktab.data.enumuration.SuggestionStatus;
import ir.maktab.data.repository.SuggestionRepository;
import ir.maktab.exception.HomeServiceException;
import ir.maktab.service.SuggestionService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Negin Mousavi
 */
@RequiredArgsConstructor
@Service
@Getter
public class SuggestionServiceImpl implements SuggestionService {
    private final SuggestionRepository suggestionRepository;

    public void saveSuggestion(Suggestion suggestion) {
        suggestionRepository.save(suggestion);
    }

    public List<Suggestion> getByStatus(Expert expert, SuggestionStatus suggestionStatus) {
        Optional<List<Suggestion>> suggestions = suggestionRepository.findBySuggestionStatusAndExpert(suggestionStatus, expert);
        if (suggestions.isEmpty())
            throw new HomeServiceException("no suggestion to show!");
        return suggestions.get();
    }

    public List<Suggestion> getAllSuggestions(Expert expert) {
        Optional<List<Suggestion>> suggestions = suggestionRepository.findByExpert(expert);
        if (suggestions.isEmpty())
            throw new HomeServiceException("you have no suggestion!");
        return suggestions.get();
    }

    public void update(Suggestion suggestion) {
        suggestionRepository.save(suggestion);
    }

    public List<Suggestion> getByOrder(Order order) {
        Optional<List<Suggestion>> suggestions = suggestionRepository.findByOrder(order);
        if (suggestions.isEmpty())
            throw new HomeServiceException("nothing to show!!");
        return suggestions.get();
    }

    public Long getCountOfRecords() {
        return suggestionRepository.count();
    }

    @Override
    public List<Suggestion> getSortedByOrder(Order order) {
        return suggestionRepository.findAll(Sort.by("suggestedPrice", "expert").descending());
    }
}
