package model.members;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

/**
 * @author Negin Mousavi
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@Entity
@NoArgsConstructor
public class Expert extends User {
    private String expertise;
    private int score;
    @Lob
    @Column(name = "picture", columnDefinition = "LONGBLOB")
    private byte[] picture;
    private double credit;
}
