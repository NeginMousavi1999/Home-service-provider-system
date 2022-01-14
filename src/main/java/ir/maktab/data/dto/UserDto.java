package ir.maktab.data.dto;

import ir.maktab.data.enumuration.UserRole;
import ir.maktab.data.enumuration.UserStatus;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * @author Negin Mousavi
 */
@Data
@SuperBuilder
public class UserDto {
    private Long identity;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private UserStatus userStatus;
    private UserRole userRole;
    private Date registrationDate;
    private double credit;
}
