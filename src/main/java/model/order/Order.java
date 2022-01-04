package model.order;

import enumuration.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.members.Customer;
import model.members.Expert;
import model.services.SubService;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.*;

/**
 * @author Negin Mousavi
 */
@Entity(name = "System_Order")
@Data
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
