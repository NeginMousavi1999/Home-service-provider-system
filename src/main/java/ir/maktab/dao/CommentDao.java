package ir.maktab.dao;

import ir.maktab.model.order.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Negin Mousavi
 */
@Repository
public interface CommentDao extends CrudRepository<Comment, Integer> {

}
