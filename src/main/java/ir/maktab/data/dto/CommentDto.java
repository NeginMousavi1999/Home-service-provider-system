package ir.maktab.data.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author Negin Mousavi
 */
@Data
@Builder
public class CommentDto {
    private Long identity;
    private String comment;
    private UserDto customer;
    private UserDto expert;
    private OrderDto order;
}
