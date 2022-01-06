package view;

import dto.UserDto;
import enumuration.UserRole;
import enumuration.UserStatus;
import lombok.Data;
import model.members.Customer;
import model.members.User;
import model.members.UserViewRequest;
import service.UserService;
import validation.Validation;

import java.util.List;

import static enumuration.UserRole.valueOf;

/**
 * @author Negin Mousavi
 */
@Data
public class UserView {
    private ManagerView managerView;
    private ExpertView expertView;
    private CustomerView customerView;
    private Validation validation;
    private UserService userService;

    public User createUser(String firstName, String lastName, String email, String password, String role,
                           double credit, String expertise, String avatarName) {

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
            case CUSTOMER:
                user = customerView.createCustomer(user, credit);
                break;
            case EXPERT:
                user = expertView.createExpert(user, expertise, avatarName);
                break;
            default:
                System.out.println("invalid input!");
        }
        return user;
    }

    public boolean login(String username, String password) {
        User user;
        try {
            user = userService.findUserByUserNameAndPassword(username, password);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return false;
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
        return true;
    }

    public List<UserDto> returnUsersWithFiltering(UserViewRequest request) {
        return userService.returnUsersFiltering(request);
    }

    public boolean changePassword(Customer customer, String oldPass, String newPass) {
        try {
            validation.validateUserRole(UserRole.CUSTOMER, customer.getUserRole());
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return false;
        }

        try {
            validation.validateCorrectPassword(oldPass, customer.getPassword());
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return false;
        }

        try {
            validation.validatePassword(newPass);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return false;
        }
        return customerView.changePassword(customer, newPass);
    }
}
