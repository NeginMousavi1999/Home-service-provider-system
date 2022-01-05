package dao;

import model.members.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * @author Negin Mousavi
 */
public class CustomerDao extends UserDao {
    public void update(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(customer);
        transaction.commit();
        session.close();
    }

    @Override
    public Customer findByEmail(String email) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from Customer c where c.email=:email";
        Query<Customer> query = session.createQuery(hql, Customer.class);
        query.setParameter("email", email);
        List<Customer> list = query.list();
        transaction.commit();
        session.close();
        if (list.size() == 0)
            return null;
        return list.get(0);
    }

    public Customer findById(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = session.get(Customer.class, id);
        transaction.commit();
        session.close();
        return customer;
    }
}
