package model.members;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import model.order.Order;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Negin Mousavi
 */
@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@SuperBuilder
@Entity
@NoArgsConstructor
public class Customer extends User {
    private double credit;
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private Set<Order> orders = new HashSet<>();
}
