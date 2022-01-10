package ir.maktab.service.implementation;

import ir.maktab.dao.CommentDao;
import ir.maktab.model.order.Comment;
import ir.maktab.service.CommentService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Negin Mousavi
 */
@RequiredArgsConstructor
@Getter
@Service
public class CommentServiceImp implements CommentService {
    private final CommentDao commentDao;

    public void save(Comment comment) {
        commentDao.save(comment);
    }

    public Long getCountOfRecords() {
        return commentDao.count();
    }
}
