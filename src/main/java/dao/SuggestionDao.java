package dao;

import enumuration.SuggestionStatus;
import model.members.Expert;
import model.order.Suggestion;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * @author Negin Mousavi
 */
public class SuggestionDao extends BaseDao {
    public void create(Suggestion suggestion) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(suggestion);
        session.saveOrUpdate(suggestion.getOrder());
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
        session.saveOrUpdate(suggestion.getOrder());
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

    public List<Suggestion> getByStatus(Expert expert, SuggestionStatus status) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from Suggestion s where s.expert=:expert and s.suggestionStatus=:status";
        Query<Suggestion> query = session.createQuery(hql, Suggestion.class);
        query.setParameter("expert", expert);
        query.setParameter("status", status);
        List<Suggestion> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }

    public List<Suggestion> getAllSuggestions(Expert expert) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from Suggestion s where s.expert=:expert";
        Query<Suggestion> query = session.createQuery(hql, Suggestion.class);
        query.setParameter("expert", expert);
        List<Suggestion> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }
}
