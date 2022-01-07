package dao;

import model.services.SubService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

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

    public SubService read(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        SubService subService = session.get(SubService.class, id);
        transaction.commit();
        session.close();
        return subService;
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

    public List<String> getAllName() {//TODO
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "select s.name from SubService s";
        Query<String> query = session.createQuery(hql, String.class);
        List<String> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }

    public SubService findByName(String name) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from SubService s where s.name=:name";
        Query<SubService> query = session.createQuery(hql, SubService.class);
        query.setParameter("name", name);
        List<SubService> list = query.list();
        transaction.commit();
        session.close();
        if (list.size() == 0)
            return null;
        return list.get(0);
    }
}
