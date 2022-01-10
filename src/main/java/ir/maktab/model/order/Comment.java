package ir.maktab.model.order;

import ir.maktab.model.members.Customer;
import ir.maktab.model.members.Expert;
import lombok.*;

import javax.persistence.*;

/**
 * @author Negin Mousavi
 */
@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String comment;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Expert expert;

    @OneToOne
    private Order order;
}