package model.members;

import enumuration.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Negin Mousavi
 */
@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    protected long id;
    @Column(name = "first_name")
    protected String firstName;
    @Column(name = "last_name")
    protected String lastName;
    @Column(nullable = false)
    protected String email;
    @Column(unique = true)
    protected String password;
    @Column(name = "user_role")
    @Enumerated(value = EnumType.STRING)
    protected UserRole userRole;
    @Column(name = "registration_date")
    @CreationTimestamp
    protected Date registrationDate;
}
