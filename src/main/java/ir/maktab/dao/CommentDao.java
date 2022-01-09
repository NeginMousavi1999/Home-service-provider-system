package ir.maktab.dao;

import ir.maktab.model.order.Comment;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

/**
 * @author Negin Mousavi
 */
@Component
public class CommentDao extends BaseDao {
    public void create(Comment comment) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(comment);
        transaction.commit();
        session.close();
    }

    public Comment read(Comment comment) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Comment foundComment = session.get(Comment.class, comment.getId());
        transaction.commit();
        session.close();
        return foundComment;
    }

    public void update(Comment comment) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(comment);
        transaction.commit();
        session.close();
    }

    public void delete(Comment comment) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(comment);
        transaction.commit();
        session.close();
    }
}
