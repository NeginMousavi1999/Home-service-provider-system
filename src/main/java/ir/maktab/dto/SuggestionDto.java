package ir.maktab.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author Negin Mousavi
 */
@Data
public class SuggestionDto {
    private UserDto expert;
    private OrderDto order;
    private Date registrationDate;
    private double suggestedPrice;
    private int durationOfWork;
    private Date startTime;
}
