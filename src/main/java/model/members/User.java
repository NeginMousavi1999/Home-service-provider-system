package model.members;

import enumuration.UserStatus;
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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
    @Column(name = "first_name")
    protected String firstName;
    @Column(name = "last_name")
    protected String lastName;
    protected String email;
    protected String password;
    @Column(name = "user_status")
    @Enumerated(value = EnumType.STRING)
    protected UserStatus userStatus;
    @Column(name = "registration_date")
    @CreationTimestamp
    protected Date registrationDate;
}
