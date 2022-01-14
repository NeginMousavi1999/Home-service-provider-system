package ir.maktab.model.members;

import ir.maktab.model.services.Service;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
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
public class Expert extends User implements Comparable<Expert> {
    @Lob
    @Column(columnDefinition = "BLOB", length = 300000)
    private byte[] picture;

    @ManyToMany
    private Set<Service> services = new HashSet<>();

    private double score;

    @Override
    public int compareTo(Expert o) {
        return Double.compare(this.score, o.score);
    }
}
