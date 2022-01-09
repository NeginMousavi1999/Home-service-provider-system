package ir.maktab.service;

import ir.maktab.dao.CommentDao;
import ir.maktab.model.order.Comment;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Negin Mousavi
 */
@RequiredArgsConstructor
@Service
@Getter
public class CommentService {
    private final CommentDao commentDao;

    public void save(Comment comment) {
        commentDao.create(comment);
    }
}
