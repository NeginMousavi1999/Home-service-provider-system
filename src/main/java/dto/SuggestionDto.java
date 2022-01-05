package dto;

import lombok.Data;
import model.members.Expert;
import model.order.Order;

import java.util.Date;

/**
 * @author Negin Mousavi
 */
@Data
public class SuggestionDto {
    private int id;
    private Expert expert;
    private Order order;
    private Date registrationDate;
    private double suggestedPrice;
    private int durationOfWork;
    private Date startTime;
}
