package service;

import dao.UserDao;
import dto.UserDto;
import exception.HomeServiceException;
import lombok.Data;
import model.members.User;
import dto.UserRequestDto;
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
    private ModelMapper modelMapper;

    public User findUserByUserNameAndPassword(String email, String password) {
        User user = userDao.findByEmailAndPassword(email, password);
        if (user == null)
            throw new HomeServiceException("username or password is incorrect");
        return user;
    }

    public List<UserDto> returnUsersFiltering(UserRequestDto request) {//TODO must be dto
        return userDao.returnUsersFiltering(request).stream().map(this::createUserDto).collect(Collectors.toList());
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
