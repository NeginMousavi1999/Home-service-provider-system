package ir.maktab.service.implementation;

import ir.maktab.dao.SuggestionDao;
import ir.maktab.enumuration.SuggestionStatus;
import ir.maktab.exception.HomeServiceException;
import ir.maktab.model.members.Expert;
import ir.maktab.model.order.Order;
import ir.maktab.model.order.Suggestion;
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
    private final SuggestionDao suggestionDao;

    public void saveSuggestion(Suggestion suggestion) {
        suggestionDao.save(suggestion);
    }

    public List<Suggestion> getByStatus(Expert expert, SuggestionStatus suggestionStatus) {
        List<Suggestion> suggestions = suggestionDao.findBySuggestionStatusAndExpert(suggestionStatus, expert);
        if (suggestions.size() == 0)
            throw new HomeServiceException("no suggestion to show!");
        return suggestions;
    }

    public List<Suggestion> getAllSuggestions(Expert expert) {
        List<Suggestion> suggestions = suggestionDao.findByExpert(expert);
        if (suggestions.size() == 0)
            throw new HomeServiceException("you have no suggestion!");
        return suggestions;
    }

    public void update(Suggestion suggestion) {
        suggestionDao.save(suggestion);
    }

    public List<Suggestion> getByOrder(Order order) {
        return suggestionDao.findByOrder(order);
    }

    public Long getCountOfRecords() {
        return suggestionDao.count();
    }

    @Override
    public List<Suggestion> getSortedByOrder(Order order) {
        return suggestionDao.findAll(Sort.by("suggestedPrice", "expert").descending());
    }
}
