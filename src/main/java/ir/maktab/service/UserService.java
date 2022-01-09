package ir.maktab.service;

import ir.maktab.dao.UserDao;
import ir.maktab.dto.UserDto;
import ir.maktab.dto.UserRequestDto;
import ir.maktab.exception.HomeServiceException;
import ir.maktab.model.members.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Negin Mousavi
 */
@RequiredArgsConstructor
@Service
@Getter
public class UserService {
    private final UserDao userDao;
    private final ModelMapper modelMapper = new ModelMapper();

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
