package dao;

import model.members.Expert;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * @author Negin Mousavi
 */
public class ExpertDao extends UserDao {

    public void createExpert(Expert expert) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(expert);
        transaction.commit();
        session.close();
    }

    public void update(Expert expert) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(expert);
        transaction.commit();
        session.close();
    }

    public Expert findById(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Expert expert = session.get(Expert.class, id);
        transaction.commit();
        session.close();
        return expert;
    }

    @Override
    public Expert findByEmail(String email) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from Expert e where e.email=:email";
        Query<Expert> query = session.createQuery(hql, Expert.class);
        query.setParameter("email", email);
        List<Expert> list = query.list();
        transaction.commit();
        session.close();
        if (list.size() == 0)
            return null;
        return list.get(0);
    }
}
