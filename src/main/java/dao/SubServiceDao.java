package dao;

import model.services.SubService;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author Negin Mousavi
 */
public class SubServiceDao extends BaseDao {
    public void create(SubService subService) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(subService);
        transaction.commit();
        session.close();
    }

    public SubService read(SubService subService) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        SubService foundSubService = session.get(SubService.class, subService.getId());
        transaction.commit();
        session.close();
        return foundSubService;
    }

    public void update(SubService subService) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(subService);
        transaction.commit();
        session.close();
    }

    public void delete(SubService subService) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(subService);
        transaction.commit();
        session.close();
    }
}
