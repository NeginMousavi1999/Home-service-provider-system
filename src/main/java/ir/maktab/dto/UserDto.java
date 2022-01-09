package ir.maktab.dto;

import ir.maktab.enumuration.UserRole;
import ir.maktab.enumuration.UserStatus;
import lombok.Data;

import java.util.Date;

/**
 * @author Negin Mousavi
 */
@Data
public class UserDto {
    protected String firstName;
    protected String lastName;
    protected String email;
    protected UserStatus userStatus;
    protected UserRole userRole;
    protected Date registrationDate;
}
