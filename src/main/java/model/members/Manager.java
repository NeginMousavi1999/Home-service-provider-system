package model.members;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

/**
 * @author Negin Mousavi
 */
@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@SuperBuilder
@Entity
@NoArgsConstructor
public class Manager extends User {

}
