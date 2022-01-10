package ir.maktab.service.implementation;

import ir.maktab.dao.CustomerDao;
import ir.maktab.dto.UserDto;
import ir.maktab.exception.HomeServiceException;
import ir.maktab.model.members.Customer;
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

    private final CustomerDao customerDao;

    ModelMapper modelMapper = new ModelMapper();
    public void update(Customer customer) {
        customerDao.save(customer);
    }

    public void save(Customer customer) {
        try {
            customerDao.save(customer);
        } catch (Exception e) {
            throw new HomeServiceException("we have this username, so you can't use it!");
        }
    }

    public Customer findByEmail(String email) {
        Optional<Customer> customer = customerDao.findByEmail(email);
        if (customer.isEmpty())
            throw new HomeServiceException("we have not customer with this email");
        return customer.get();
    }

    public Long getCountOfRecords() {
        return customerDao.count();
    }

    @Override
    public UserDto findByEmail2(String s) {
        Customer customer = customerDao.findByEmail(s).get();
        UserDto userDto = modelMapper.map(customer, UserDto.class);
        userDto.setRandom(GenerateRandomInt.generateNumber());
        userDto.setIdentity(userDto.getRandom() + customer.getId());
        return userDto;
    }

    @Override
    public void update2(UserDto userDto) {
        Customer customer = modelMapper.map(userDto, Customer.class);
        customer.setId(userDto.getIdentity() - userDto.getRandom());
        customerDao.save(customer);
    }

}
