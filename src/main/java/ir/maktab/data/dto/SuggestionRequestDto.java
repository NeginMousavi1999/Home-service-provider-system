package ir.maktab.data.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author Negin Mousavi
 */
@Data
public class SuggestionRequestDto {
    private Date registrationDate;
    private double suggestedPrice;
    private int durationOfWork;
    private Date startTime;
}
