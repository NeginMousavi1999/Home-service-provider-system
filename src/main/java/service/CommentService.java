package service;

import dao.CommentDao;
import lombok.Data;
import model.order.Comment;

/**
 * @author Negin Mousavi
 */
@Data
public class CommentService {
    CommentDao commentDao;

    public void save(Comment comment) {
        commentDao.create(comment);
    }
}
