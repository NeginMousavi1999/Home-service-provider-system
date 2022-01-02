package model.order;

import model.members.Expert;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

/**
 * @author Negin Mousavi
 */
public class Suggestion {
    private Expert expert;
    private Order order;
    @CreationTimestamp
    private Date registrationDate;
    private double suggestedPrice;
    private int durationOfWork;
    private Date startTime;//TODO: use calender!
}
