package ir.maktab.service;

import ir.maktab.data.entity.order.Comment;

/**
 * @author Negin Mousavi
 */
public interface CommentService {
    void save(Comment comment);

    Long getCountOfRecords();
}
