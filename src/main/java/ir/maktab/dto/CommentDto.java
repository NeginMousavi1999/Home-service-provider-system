package ir.maktab.dto;

import lombok.Data;
import ir.maktab.model.members.Customer;
import ir.maktab.model.members.Expert;
import ir.maktab.model.order.Order;

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
