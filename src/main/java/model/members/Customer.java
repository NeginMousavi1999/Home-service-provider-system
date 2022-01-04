package model.members;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import model.order.Order;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Negin Mousavi
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@Entity
@NoArgsConstructor
public class Customer extends User {
    private double credit;
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private List<Order> orders = new ArrayList<>();
}
