package dao;

import model.order.Order;
import model.services.SubService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

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

    public List<Order> findBySubService(SubService subService) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from System_Order o where o.subService=:subService";
        Query<Order> query = session.createQuery(hql, Order.class);
        query.setParameter("subService", subService);
        List<Order> list = query.list();
        transaction.commit();
        session.close();
        if (list.size() == 0)
            return null;
        return list;
    }
}
