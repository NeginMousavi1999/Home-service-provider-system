package model.order;

import lombok.*;
import model.members.Customer;
import model.members.Expert;

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
