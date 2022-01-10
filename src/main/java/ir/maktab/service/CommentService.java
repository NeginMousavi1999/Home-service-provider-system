package ir.maktab.service;

import ir.maktab.model.order.Comment;

/**
 * @author Negin Mousavi
 */
public interface CommentService {
    void save(Comment comment);

    Long getCountOfRecords();
}
