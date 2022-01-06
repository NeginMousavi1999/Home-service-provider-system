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
    @JoinColumn(name = "sub_service_id")
    private SubService subService;
    private String description;
    @Column(name = "suggested_price")
    private double suggestedPrice;
    @Column(name = "final_price")
    private double finalPrice;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;
    @CreationTimestamp
    @Column(name = "registration_date")
    private Date registrationDate;
    @CreationTimestamp
    @Column(name = "to_be_done_date")
    private Date toBeDoneDate;
    private String address;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Expert expert;
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private Set<Suggestion> suggestions = new HashSet<>();
}
