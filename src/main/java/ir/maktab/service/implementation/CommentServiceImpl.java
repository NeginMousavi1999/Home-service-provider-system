package ir.maktab.service.implementation;

import ir.maktab.data.dto.CommentDto;
import ir.maktab.data.dto.OrderDto;
import ir.maktab.data.entity.order.Comment;
import ir.maktab.data.enumuration.OrderStatus;
import ir.maktab.data.repository.CommentRepository;
import ir.maktab.service.CommentService;
import ir.maktab.service.ExpertService;
import ir.maktab.service.OrderService;
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
    private final OrderService orderService;

    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    public Long getCountOfRecords() {
        return commentRepository.count();
    }

    @Override
    public void addComment(CommentDto commentDto, String commentDtoScore, String comment) {
        double score;
        if (commentDtoScore.length() != 0) {
            commentDto.setScore(commentDtoScore);
            score = Double.parseDouble(commentDtoScore);
            expertService.updateScore(commentDto.getExpert(), score);
        }
        if (comment.length() != 0) {
            commentDto.setComment(comment);
            commentRepository.save(CommentMapper.mapCommentDtoToCommentForSaving(commentDto));
        }
        OrderDto orderDto = commentDto.getOrder();
        orderDto.setOrderStatus(OrderStatus.FEEDEDBACK);
        orderService.updateStatus(orderDto);
    }
}
