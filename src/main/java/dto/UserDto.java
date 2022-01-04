package dto;

import enumuration.UserRole;
import enumuration.UserStatus;
import lombok.Data;

import java.util.Date;

/**
 * @author Negin Mousavi
 */
@Data
public class UserDto {
    protected long id;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected UserStatus userStatus;
    protected UserRole userRole;
    protected Date registrationDate;
}
