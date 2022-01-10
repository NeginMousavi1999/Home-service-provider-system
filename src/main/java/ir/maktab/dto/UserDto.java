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
    private int random;
    private Long identity;
    private String firstName;
    private String lastName;
    private String email;
    private UserStatus userStatus;
    private UserRole userRole;
    private Date registrationDate;
}
