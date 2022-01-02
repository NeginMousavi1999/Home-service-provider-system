package dao;

import model.order.Suggestion;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author Negin Mousavi
 */
public class SuggestionDao extends BaseDao {
    public void create(Suggestion suggestion) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(suggestion);
        transaction.commit();
        session.close();
    }

    public Suggestion read(Suggestion suggestion) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Suggestion foundSuggestion = session.get(Suggestion.class, suggestion.getId());
        transaction.commit();
        session.close();
        return foundSuggestion;
    }

    public void update(Suggestion suggestion) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(suggestion);
        transaction.commit();
        session.close();
    }

    public void delete(Suggestion suggestion) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(suggestion);
        transaction.commit();
        session.close();
    }
}
