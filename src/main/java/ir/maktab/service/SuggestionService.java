package ir.maktab.service;

import ir.maktab.dao.SuggestionDao;
import ir.maktab.enumuration.SuggestionStatus;
import ir.maktab.exception.HomeServiceException;
import ir.maktab.model.members.Expert;
import ir.maktab.model.order.Suggestion;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Negin Mousavi
 */
@RequiredArgsConstructor
@Service
@Getter
public class SuggestionService {
    private final SuggestionDao suggestionDao;

    public void saveSuggestion(Suggestion suggestion) {
        suggestionDao.create(suggestion);
    }

    public List<Suggestion> getByStatus(Expert expert, SuggestionStatus suggestionStatus) {
        List<Suggestion> suggestions = suggestionDao.getByStatus(expert, suggestionStatus);
        if (suggestions.size() == 0)
            throw new HomeServiceException("no suggestion to show!");
        return suggestions;
    }

    public List<Suggestion> getAllSuggestions(Expert expert) {
        List<Suggestion> suggestions = suggestionDao.getAllSuggestions(expert);
        if (suggestions.size() == 0)
            throw new HomeServiceException("you have no suggestion!");
        return suggestions;
    }

    public void update(Suggestion suggestion) {
        suggestionDao.update(suggestion);
    }
}
