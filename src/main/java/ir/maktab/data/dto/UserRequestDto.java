package ir.maktab.data.dto;

import ir.maktab.data.enumuration.UserRole;
import lombok.Builder;
import lombok.Data;

/**
 * @author Negin Mousavi
 */
@Data
@Builder
public class UserRequestDto {
    private Long identity;
    private String firstName;
    private String lastName;
    private String email;
    private UserRole userRole;
    private String serviceName;
}
