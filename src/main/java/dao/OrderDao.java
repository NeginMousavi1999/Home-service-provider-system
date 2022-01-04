package dao;

import model.order.Order;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author Negin Mousavi
 */
public class OrderDao extends BaseDao {
    public void create(Order order) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(order);
        session.saveOrUpdate(order.getCustomer());
        transaction.commit();
        session.close();
    }

    public Order read(Order order) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Order foundOrder = session.get(Order.class, order.getId());
        transaction.commit();
        session.close();
        return foundOrder;
    }

    public void update(Order order) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(order);
        transaction.commit();
        session.close();
    }

    public void delete(Order order) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(order);
        transaction.commit();
        session.close();
    }

    public Order findById(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Order order = session.get(Order.class, id);
        transaction.commit();
        session.close();
        return order;
    }
}
