package ir.maktab.dto;

import lombok.Data;
import ir.maktab.model.members.Expert;
import ir.maktab.model.order.Order;

import java.util.Date;

/**
 * @author Negin Mousavi
 */
@Data
public class SuggestionDto {
    private Expert expert;
    private Order order;
    private Date registrationDate;
    private double suggestedPrice;
    private int durationOfWork;
    private Date startTime;
}
