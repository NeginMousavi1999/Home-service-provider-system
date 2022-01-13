package ir.maktab.service.implementation;

import ir.maktab.model.order.Comment;
import ir.maktab.repository.CommentRepository;
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
    private final CommentRepository commentRepository;

    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    public Long getCountOfRecords() {
        return commentRepository.count();
    }
}
