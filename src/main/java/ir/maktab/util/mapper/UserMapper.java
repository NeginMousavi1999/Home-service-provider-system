package ir.maktab.util.mapper;

import ir.maktab.data.dto.UserDto;
import ir.maktab.data.entity.members.User;

/**
 * @author Negin Mousavi
 */
public class UserMapper {

    public static User mapServiceDtoToService(UserDto userDto) {
        return User.builder()
                .id(userDto.getIdentity() - 1000)
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .credit(userDto.getCredit())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .userRole(userDto.getUserRole())
                .userStatus(userDto.getUserStatus())
                .build();
    }

    public static UserDto mapServiceToServiceDto(User user) {
        return UserDto.builder()
                .identity(user.getId() + 1000)
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .credit(user.getCredit())
                .email(user.getEmail())
                .password(user.getPassword())
                .userRole(user.getUserRole())
                .userStatus(user.getUserStatus())
                .registrationDate(user.getRegistrationDate())
                .build();
    }
}
