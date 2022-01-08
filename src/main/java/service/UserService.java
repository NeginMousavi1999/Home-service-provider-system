package service;

import dao.UserDao;
import dto.UserDto;
import dto.UserRequestDto;
import exception.HomeServiceException;
import lombok.Data;
import model.members.User;
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

    public List<UserDto> returnUsersFiltering(UserRequestDto request) {
        return userDao.returnUsersFiltering(request).stream().map(this::createUserDto).collect(Collectors.toList());
    }

    private UserDto createUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

}
