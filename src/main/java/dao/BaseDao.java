package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

/**
 * @author Negin Mousavi
 */
@Component
public class BaseDao {
    protected static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public Long getCountOfRecordsByEntityName(String entityName) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String queryString = String.format("select count(*) from %s", entityName);
        Query query = session.createQuery(queryString);
        Long count = (Long) query.uniqueResult();
        transaction.commit();
        session.close();
        return count;
    }
}