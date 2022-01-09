package ir.maktab.model.members;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ir.maktab.model.services.Service;

import javax.persistence.*;
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
public class Expert extends User {
    @Lob
    @Column(columnDefinition = "BLOB", length = 3000)
    private byte[] picture;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Service> services = new HashSet<>();

    private double score;
}
