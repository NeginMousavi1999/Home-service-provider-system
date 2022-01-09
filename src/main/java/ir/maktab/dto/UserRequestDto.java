package ir.maktab.dto;

import ir.maktab.enumuration.UserRole;
import lombok.Builder;
import lombok.Data;

/**
 * @author Negin Mousavi
 */
@Data
@Builder
public class UserRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private UserRole userRole;
    private String serviceName;
}
