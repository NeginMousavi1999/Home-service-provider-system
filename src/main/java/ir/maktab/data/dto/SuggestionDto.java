package ir.maktab.data.dto;

import ir.maktab.data.enumuration.SuggestionStatus;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author Negin Mousavi
 */
@Data
@Builder
public class SuggestionDto {
    private Long identity;
    private ExpertDto expert;
    private OrderDto order;
    private Date registrationDate;
    private double suggestedPrice;
    private int durationOfWork;
    private Date startTime;
    private SuggestionStatus suggestionStatus;
}
