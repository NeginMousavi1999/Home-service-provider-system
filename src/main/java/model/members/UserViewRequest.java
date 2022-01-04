package model.members;

import enumuration.UserRole;
import lombok.Builder;
import lombok.Data;

/**
 * @author Negin Mousavi
 */
@Data
@Builder
public class UserViewRequest {
    private String firstName;
    private String lastName;
    private String email;
    private UserRole userRole;
    private String serviceName;
}
