package dao;

import model.members.User;
import model.members.UserViewRequest;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import java.util.List;

/**
 * @author Negin Mousavi
 */
public class UserDao extends BaseDao {
    public void create(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    public User read(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User foundUser = session.get(User.class, id);
        transaction.commit();
        session.close();
        return foundUser;
    }

    public void delete(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(user);
        transaction.commit();
        session.close();
    }

    public User findByEmailAndPassword(String email, String password) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from User u where u.email=:email and u.password=:password";
        Query<User> query = session.createQuery(hql, User.class);
        query.setParameter("email", email);
        query.setParameter("password", password);
        List<User> list = query.list();
        transaction.commit();
        session.close();
        if (list.size() == 0)
            return null;
        return list.get(0);
    }

    public User findByEmail(String email) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from User u where u.email=:email";
        Query<User> query = session.createQuery(hql, User.class);
        query.setParameter("email", email);
        List<User> list = query.list();
        transaction.commit();
        session.close();
        if (list.size() == 0)
            return null;
        return list.get(0);
    }

    public List<User> returnUsersFiltering(UserViewRequest request) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(User.class, "u");
        criteria.createAlias("u.services", "s");

        addRestrictionIfNotNull(criteria, "u.firstName", request.getFirstName());
        addRestrictionIfNotNull(criteria, "u.lastName", request.getLastName());
        addRestrictionIfNotNull(criteria, "u.email", request.getEmail());
        addRestrictionIfNotNull(criteria, "u.userRole", request.getUserRole());
        addRestrictionIfNotNull(criteria, "s.name", request.getServiceName());

        List<User> list = criteria.list();
        transaction.commit();
        session.close();
        return list;
    }

    private void addRestrictionIfNotNull(Criteria criteria, String propertyName, Object value) {
        if (value != null) {
            criteria.add(Restrictions.eq(propertyName, value));
        }
    }
}
