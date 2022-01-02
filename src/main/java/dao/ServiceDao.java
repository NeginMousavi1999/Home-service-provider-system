package dao;

import model.services.Service;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author Negin Mousavi
 */
public class ServiceDao extends BaseDao {
    public void create(Service service) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(service);
        transaction.commit();
        session.close();
    }

    public Service read(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Service service = session.get(Service.class, id);
        transaction.commit();
        session.close();
        return service;
    }

    public void update(Service service) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(service);
        transaction.commit();
        session.close();
    }

    public void delete(Service service) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(service);
        transaction.commit();
        session.close();
    }
}
