package model.order;

import enumuration.OrderStatus;
import lombok.*;
import model.members.Customer;
import model.members.Expert;
import model.services.SubService;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Negin Mousavi
 */
@Entity(name = "System_Order")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private SubService subService;
    private String description;
    private double suggestedPrice;
    private double finalPrice;
    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;
    @CreationTimestamp
    private Date registrationDate;
    @CreationTimestamp
    private Date toBeDoneDate;
    private String address;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Expert expert;
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private Set<Suggestion> suggestions = new HashSet<>();
}
