package model.order;

import lombok.*;
import model.members.Expert;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Negin Mousavi
 */
@Entity
@Setter
@Getter
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
