package ir.maktab.util.mapper;

import ir.maktab.data.dto.ExpertDto;
import ir.maktab.data.entity.members.Expert;

/**
 * @author Negin Mousavi
 */
public class ExpertMapper {
    private static final int suffix = 1000;

    public static Expert mapExpertDtoToExpert(ExpertDto customerDto) {
        return Expert.builder()
                .id(customerDto.getIdentity() - suffix)
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .email(customerDto.getEmail())
                .password(customerDto.getPassword())
                .credit(customerDto.getCredit())
                .userStatus(customerDto.getUserStatus())
                .userRole(customerDto.getUserRole())
                .registrationDate(customerDto.getRegistrationDate())
                .picture(customerDto.getPicture())
                .build();
    }

    public static ExpertDto mapExpertToExpertDto(Expert customer) {
        return ExpertDto.builder()
                .identity(customer.getId() + suffix)
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .password(customer.getPassword())
                .credit(customer.getCredit())
                .userStatus(customer.getUserStatus())
                .userRole(customer.getUserRole())
                .registrationDate(customer.getRegistrationDate())
                .picture(customer.getPicture())
                .build();
    }
}
