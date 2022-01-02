package model.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.members.Expert;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Negin Mousavi
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Suggestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private Expert expert;
    @ManyToOne
    private Order order;
    @CreationTimestamp
    private Date registrationDate;
    private double suggestedPrice;
    private int durationOfWork;
    private Date startTime;//TODO: use calender!
}
