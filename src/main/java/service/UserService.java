package service;

import dao.UserDao;
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
    private UserDao userDao;

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

    public User findUserByUserNameAndPassword(String email, String password) {
        User user = userDao.findByEmailAndPassword(email, password);
        if (user == null)
            throw new RuntimeException("username or password is incorrect");
        return user;
    }
}
