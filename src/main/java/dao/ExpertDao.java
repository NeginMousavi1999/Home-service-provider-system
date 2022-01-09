package dao;

import model.members.Expert;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Negin Mousavi
 */
@Component
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

    public double getScore(Expert expert) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "select e.score from Expert e where e.id=:id";
        Query<Double> query = session.createQuery(hql, Double.class);
        query.setParameter("id", expert.getId());
        List<Double> list = query.list();
        transaction.commit();
        session.close();
        return list.get(0);
    }
}
