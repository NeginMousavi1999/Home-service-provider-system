package ir.maktab.data.repository;

import ir.maktab.data.entity.order.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Negin Mousavi
 */
@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {

}
