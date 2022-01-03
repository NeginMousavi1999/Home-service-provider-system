package view;

import enumuration.UserRole;
import enumuration.UserStatus;
import lombok.Data;
import model.members.User;
import service.UserService;
import validation.Validation;

import static enumuration.UserRole.valueOf;

/**
 * @author Negin Mousavi
 */
@Data
public class UserView {
    ManagerView managerView;
    ExpertView expertView;
    CustomerView customerView;
    Validation validation;
    UserService userService;

    public User createUser(String firstName, String lastName, String email, String password, String role) {

        try {
            validation.validateEmail(email);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return null;
        }

        try {
            validation.validatePassword(password);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return null;
        }

        UserRole userRole;
        try {
            userRole = valueOf(role);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getLocalizedMessage());
            return null;
        }

        User user = User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .userRole(userRole)
                .userStatus(UserStatus.NEW)
                .build();

        switch (userRole) {
            case MANAGER:
                user = managerView.createManager(user);
                break;
            case CUSTOMER:
                user = customerView.createCustomer(user);
                break;
            case EXPERT:
                user = expertView.createExpert(user);
                break;
            default:
                System.out.println("invalid input!");
        }
        return user;
    }

    public void login(String username, String password) {
        User user;
        try {
            user = userService.findUserByUserNameAndPassword(username, password);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return;
        }
        switch (user.getUserRole()) {
            case EXPERT:
                expertView.showPanel(user);
                break;
            case MANAGER:
                managerView.showPanel(user);
                break;
            case CUSTOMER:
                customerView.showPanel(user);
                break;
        }
    }
}
