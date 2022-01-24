package ir.maktab.service;

import ir.maktab.data.dto.LoginDto;
import ir.maktab.data.dto.UserDto;
import ir.maktab.data.dto.UserRequestDto;
import ir.maktab.data.entity.members.User;
import ir.maktab.data.enumuration.UserStatus;

import java.util.List;

/**
 * @author Negin Mousavi
 */

public interface UserService {
    List<UserDto> returnUsersFiltering(UserRequestDto request);

    UserDto createUserDto(User user);

    UserDto findUserByUserNameAndPassword(LoginDto loginDto);

    List<UserDto> returnWaitingUsers();

    void updateUserStatus(int identity, UserStatus userStatus);
}
