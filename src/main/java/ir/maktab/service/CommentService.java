package ir.maktab.service;

import ir.maktab.dao.CommentDao;
import lombok.Data;
import ir.maktab.model.order.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Negin Mousavi
 */
@Data
@Service
public class CommentService {
    @Autowired
    CommentDao commentDao;

    public void save(Comment comment) {
        commentDao.create(comment);
    }
}
