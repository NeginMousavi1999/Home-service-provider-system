package ir.maktab.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Negin Mousavi
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private Long identity;
    private String comment;
    private CustomerDto customer;
    private ExpertDto expert;
    private OrderDto order;
    private String score;
}
