package service;

import enumuration.UserRole;
import lombok.Data;
import model.members.User;
import validation.Validation;

/**
 * @author Negin Mousavi
 */
@Data
public class UserService {
    private Validation validation;
    private CustomerService customerService;

    public void changePassword(User user, String oldPass, String newPass) {
        try {
            validation.validateUserRole(UserRole.CUSTOMER, user.getUserRole());
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return;
        }

        try {
            validation.validateCorrectPassword(oldPass, user.getPassword());
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return;
        }

        try {
            validation.validatePassword(newPass);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return;
        }

        customerService.changePassword(user, newPass);
    }
}
