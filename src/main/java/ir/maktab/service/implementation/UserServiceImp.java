package ir.maktab.service.implementation;

import ir.maktab.data.dto.UserDto;
import ir.maktab.data.dto.UserRequestDto;
import ir.maktab.exception.HomeServiceException;
import ir.maktab.data.entity.members.User;
import ir.maktab.data.repository.UserRepository;
import ir.maktab.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Negin Mousavi
 */
@RequiredArgsConstructor
@Service
@Getter
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public User findUserByUserNameAndPassword(String email, String password) {
        Optional<User> user = userRepository.findByEmailAndPassword(email, password);
        if (user.isEmpty())
            throw new HomeServiceException("username or password is incorrect");
        return user.get();
    }

    public List<UserDto> returnUsersFiltering(UserRequestDto request) {
        return userRepository.findAll(UserRepository.selectByConditions(request))
                .stream().map(this::createUserDto).collect(Collectors.toList());
    }

    public UserDto createUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

}
