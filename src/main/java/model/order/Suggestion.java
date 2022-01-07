package model.order;

import enumuration.SuggestionStatus;
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
    @ManyToOne
    private Expert expert;
    @ManyToOne
    private Order order;
    @CreationTimestamp
    @Column(name = "registration_date")
    private Date registrationDate;
    @Column(name = "suggested_price")
    private double suggestedPrice;
    @Column(name = "duration_of_work")
    private int durationOfWork;
    @Column(name = "start_time")
    private Date startTime;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "suggested_status")
    private SuggestionStatus suggestionStatus;
}
