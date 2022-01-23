package ir.maktab.service.implementation;

import ir.maktab.data.dto.CommentDto;
import ir.maktab.data.entity.order.Comment;
import ir.maktab.data.repository.CommentRepository;
import ir.maktab.service.CommentService;
import ir.maktab.service.ExpertService;
import ir.maktab.util.mapper.CommentMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Negin Mousavi
 */
@RequiredArgsConstructor
@Getter
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final ExpertService expertService;

    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    public Long getCountOfRecords() {
        return commentRepository.count();
    }

    @Override
    public void addComment(CommentDto commentDto) {
        double score;
        String commentDtoScore = commentDto.getScore();
        if (commentDtoScore != null) {
            score = Double.parseDouble(commentDtoScore);
            expertService.updateScore(commentDto.getExpert(), score);
        }
        String comment = commentDto.getComment();
        if (comment.length() != 0) {
            commentRepository.save(CommentMapper.mapCommentDtoToCommentForSaving(commentDto));
        }
    }
}
