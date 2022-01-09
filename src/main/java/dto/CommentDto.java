package dto;

import lombok.Data;
import model.members.Customer;
import model.members.Expert;
import model.order.Order;

/**
 * @author Negin Mousavi
 */
@Data
public class CommentDto {
    private String comment;
    private Customer customer;
    private Expert expert;
    private Order order;
}
