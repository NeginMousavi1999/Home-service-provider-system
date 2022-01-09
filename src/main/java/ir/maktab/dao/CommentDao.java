package ir.maktab.dao;

import ir.maktab.model.order.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Negin Mousavi
 */
@Repository
public interface CommentDao extends JpaRepository<Comment, Integer> {

}
