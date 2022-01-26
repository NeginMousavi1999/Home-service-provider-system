package ir.maktab.util.mapper;

import ir.maktab.data.dto.CommentDto;
import ir.maktab.data.entity.order.Comment;

/**
 * @author Negin Mousavi
 */
public class CommentMapper {
    private static final int suffix = 1000;

    public static Comment mapCommentDtoToComment(CommentDto commentDto) {
        return Comment.builder()

                .build();
    }

    public static Comment mapCommentDtoToCommentForSaving(CommentDto commentDto) {
        return Comment.builder()
                .comment(commentDto.getComment())
                .expert(ExpertMapper.mapExpertDtoToExpert(commentDto.getExpert()))
                .customer(CustomerMapper.mapCustomerDtoToCustomer(commentDto.getCustomer()))
                .order(OrderMapper.mapOrderDtoToOrderWithId(commentDto.getOrder()))
                .build();
    }

    public static CommentDto mapCommentToCommentDto(Comment comment) {
        return CommentDto.builder()

                .build();
    }
}
