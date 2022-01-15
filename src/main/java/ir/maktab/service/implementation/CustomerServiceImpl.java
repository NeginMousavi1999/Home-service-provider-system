package ir.maktab.service.implementation;

import ir.maktab.data.dto.CustomerDto;
import ir.maktab.data.entity.members.Customer;
import ir.maktab.data.repository.CustomerRepository;
import ir.maktab.exception.HomeServiceException;
import ir.maktab.service.CustomerService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
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
    ModelMapper modelMapper = new ModelMapper();

    public void update(CustomerDto customerDto) {
        Customer customer = modelMapper.map(customerDto, Customer.class);
        customerRepository.save(customer);
    }

    public void save(CustomerDto customerDto) {
        Customer customer = modelMapper.map(customerDto, Customer.class);
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
        PropertyMap<Customer, CustomerDto> propertyMap = new PropertyMap<>() {
            @Override
            protected void configure() {
                skip(destination.getOrders());
            }
        };
        TypeMap<Customer, CustomerDto> typeMap = modelMapper.getTypeMap(Customer.class, CustomerDto.class);
        if (typeMap == null)
            modelMapper.addMappings(propertyMap);
        return modelMapper.map(customer.get(), CustomerDto.class);
    }

    public Long getCountOfRecords() {
        return customerRepository.count();
    }

}
