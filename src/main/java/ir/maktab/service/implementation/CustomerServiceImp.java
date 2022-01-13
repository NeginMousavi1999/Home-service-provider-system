package ir.maktab.service.implementation;

import ir.maktab.dto.UserDto;
import ir.maktab.exception.HomeServiceException;
import ir.maktab.model.members.Customer;
import ir.maktab.repository.CustomerRepository;
import ir.maktab.service.CustomerService;
import ir.maktab.util.GenerateRandomInt;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Negin Mousavi
 */
@Service
@RequiredArgsConstructor
@Getter
public class CustomerServiceImp implements CustomerService {

    private final CustomerRepository customerRepository;

    ModelMapper modelMapper = new ModelMapper();

    public void update(Customer customer) {
        customerRepository.save(customer);
    }

    public void save(Customer customer) {
        try {
            customerRepository.save(customer);
        } catch (Exception e) {
            throw new HomeServiceException("we have this username, so you can't use it!");
        }
    }

    public Customer findByEmail(String email) {
        Optional<Customer> customer = customerRepository.findByEmail(email);
        if (customer.isEmpty())
            throw new HomeServiceException("we have not customer with this email");
        return customer.get();
    }

    public Long getCountOfRecords() {
        return customerRepository.count();
    }

    @Override
    public UserDto findByEmailTestForDto(String s) {
        Customer customer = customerRepository.findByEmail(s).get();
        UserDto userDto = modelMapper.map(customer, UserDto.class);
        userDto.setRandom(GenerateRandomInt.generateNumber());
        userDto.setIdentity(userDto.getRandom() + customer.getId());
        return userDto;
    }

    @Override
    public void updateTestForDto(UserDto userDto) {
        Customer customer = modelMapper.map(userDto, Customer.class);
        customer.setId(userDto.getIdentity() - userDto.getRandom());
        customerRepository.save(customer);
    }

}
