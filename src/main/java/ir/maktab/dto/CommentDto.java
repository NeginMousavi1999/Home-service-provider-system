package ir.maktab.dto;

import lombok.Data;

/**
 * @author Negin Mousavi
 */
@Data
public class CommentDto {
    private String comment;
    private UserDto customer;
    private UserDto expert;
    private OrderDto order;
}
