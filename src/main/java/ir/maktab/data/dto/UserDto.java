package ir.maktab.data.dto;

import ir.maktab.data.enumuration.UserRole;
import ir.maktab.data.enumuration.UserStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * @author Negin Mousavi
 */
@NoArgsConstructor
@Data
@SuperBuilder
public class UserDto {
    private Long identity;

    @NotBlank(message = "should not be empty")
    @Size(max = 25)
    private String firstName;

    @NotBlank(message = "should not be empty")
    @Size(max = 25)
    private String lastName;

    @NotBlank(message = "should not be empty")
    @Email
    private String email;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8}$"
            , message = "the password must be at least 8 character, with a lower case, an upper case and no whitespace")
    private String password;

    private UserStatus userStatus;

    private UserRole userRole;

    @NotNull(message = "should not be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date registrationDate;

    private double credit;

    private byte[] picture;
}
