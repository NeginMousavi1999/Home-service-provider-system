package dao;


import model.order.Address;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

/**
 * @author Negin Mousavi
 */
@Component
public class AddressDao extends BaseDao {
    public void create(Address address) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(address);
        transaction.commit();
        session.close();
    }

    public Address read(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Address address = session.get(Address.class, id);
        transaction.commit();
        session.close();
        return address;
    }

    public void update(Address address) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(address);
        transaction.commit();
        session.close();
    }

    public void delete(Address address) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(address);
        transaction.commit();
        session.close();
    }

}
