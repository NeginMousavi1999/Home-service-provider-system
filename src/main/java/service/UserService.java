package service;

import dao.UserDao;
import dto.UserDto;
import enumuration.UserRole;
import lombok.Data;
import model.members.User;
import model.members.UserViewRequest;
import org.modelmapper.ModelMapper;
import validation.Validation;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Negin Mousavi
 */
@Data
public class UserService {
    private Validation validation;
    private CustomerService customerService;
    private UserDao userDao;
    private final ModelMapper modelMapper = new ModelMapper();

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

    public List<UserDto> showUsersFiltering(UserViewRequest request) {
        return userDao.showUsersFiltering(request).stream().map(this::createUserDto).collect(Collectors.toList());
    }

    private UserDto createUserDto(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setRegistrationDate(user.getRegistrationDate());
        userDto.setUserRole(user.getUserRole());
        userDto.setUserStatus(user.getUserStatus());
        return userDto;
    }

}
