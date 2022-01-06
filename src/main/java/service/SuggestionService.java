package service;

import dao.SuggestionDao;
import enumuration.SuggestionStatus;
import lombok.Data;
import model.members.Expert;
import model.order.Suggestion;

import java.util.List;

/**
 * @author Negin Mousavi
 */
@Data
public class SuggestionService {
    SuggestionDao suggestionDao;

    public void saveSuggestion(Suggestion suggestion) {
        suggestionDao.create(suggestion);
    }

    public List<Suggestion> getByStatus(Expert expert, SuggestionStatus suggestionStatus) {
        List<Suggestion> suggestions = suggestionDao.getByStatus(expert, suggestionStatus);
        if (suggestions.size() == 0)
            throw new RuntimeException("no suggestion to show!");
        return suggestions;
    }

    public List<Suggestion> getAllSuggestions(Expert expert) {
        List<Suggestion> suggestions = suggestionDao.getAllSuggestions(expert);
        if (suggestions.size() == 0)
            throw new RuntimeException("you have no suggestion!");
        return suggestions;
    }

    public void update(Suggestion suggestion) {
        suggestionDao.update(suggestion);
    }
}
