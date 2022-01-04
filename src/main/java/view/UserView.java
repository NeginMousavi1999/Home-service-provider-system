package view;

import dto.UserDto;
import enumuration.UserRole;
import enumuration.UserStatus;
import lombok.Data;
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

    public List<UserDto> showUsersFiltering() {
        UserViewRequest request = getUserViewRequest();
        return userService.showUsersFiltering(request);
    }

    private UserViewRequest getUserViewRequest() {
        return UserViewRequest.builder()
/*                .firstName("jack")
                .lastName("jack")
                .email("jack@gmail.com")
                .expertise("decorate")
                .userRole(UserRole.EXPERT)*/


                .firstName("jack")
                .lastName("jack")
                .email("jack@gmail.com")
//                .userRole(CUSTOMER)
                .expertise("decorate")

                .build();
    }
}
