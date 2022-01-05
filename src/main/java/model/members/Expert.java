package model.members;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import model.services.Service;

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
    private double score;
    @Lob
    @Column(name = "picture", columnDefinition = "LONGBLOB")
    private byte[] picture;
    private double credit;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Service> services = new HashSet<>();
}
