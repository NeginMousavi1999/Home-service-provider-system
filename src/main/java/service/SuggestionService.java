package service;

import dao.SuggestionDao;
import lombok.Data;
import model.order.Suggestion;

/**
 * @author Negin Mousavi
 */
@Data
public class SuggestionService {
    SuggestionDao suggestionDao;

    public void saveSuggestion(Suggestion suggestion) {
        suggestionDao.create(suggestion);
    }
}
