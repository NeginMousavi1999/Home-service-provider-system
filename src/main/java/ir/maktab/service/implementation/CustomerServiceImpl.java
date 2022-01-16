package ir.maktab.service.implementation;

import ir.maktab.data.dto.CustomerDto;
import ir.maktab.data.entity.members.Customer;
import ir.maktab.data.repository.CustomerRepository;
import ir.maktab.exception.HomeServiceException;
import ir.maktab.service.CustomerService;
import ir.maktab.util.mapper.CustomerMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Negin Mousavi
 */
@Service
@RequiredArgsConstructor
@Getter
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    public void update(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapCustomerDtoToCustomer(customerDto);
        customerRepository.save(customer);
    }

    public void save(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapCustomerDtoToCustomer(customerDto);
        try {
            customerRepository.save(customer);
        } catch (Exception e) {
            throw new HomeServiceException("we have this username, so you can't use it!");
        }
    }

    public CustomerDto findByEmail(String email) {
        Optional<Customer> customer = customerRepository.findByEmail(email);
        if (customer.isEmpty())
            throw new HomeServiceException("we have not customer with this email");
        return CustomerMapper.mapCustomerToCustomerDto(customer.get());
    }

    public Long getCountOfRecords() {
        return customerRepository.count();
    }

}
