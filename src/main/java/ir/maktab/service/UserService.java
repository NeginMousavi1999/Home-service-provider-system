package ir.maktab.service;

import ir.maktab.dto.UserDto;
import ir.maktab.dto.UserRequestDto;
import ir.maktab.model.members.User;

import java.util.List;

/**
 * @author Negin Mousavi
 */

public interface UserService {
    List<UserDto> returnUsersFiltering(UserRequestDto request);

    UserDto createUserDto(User user);

    User findUserByUserNameAndPassword(String email, String password);
}
