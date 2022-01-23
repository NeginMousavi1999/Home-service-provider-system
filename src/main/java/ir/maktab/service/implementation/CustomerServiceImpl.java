package ir.maktab.service.implementation;

import ir.maktab.data.dto.CustomerDto;
import ir.maktab.data.dto.ExpertDto;
import ir.maktab.data.dto.OrderDto;
import ir.maktab.data.entity.members.Customer;
import ir.maktab.data.enumuration.OrderStatus;
import ir.maktab.data.enumuration.UserRole;
import ir.maktab.data.repository.CustomerRepository;
import ir.maktab.exception.HomeServiceException;
import ir.maktab.service.CustomerService;
import ir.maktab.service.ExpertService;
import ir.maktab.service.OrderService;
import ir.maktab.util.mapper.CustomerMapper;
import ir.maktab.validation.Validation;
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
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final Validation validation;
    private final ExpertService expertService;
    private final OrderService orderService;
    private final ModelMapper modelMapper = new ModelMapper();

    public void update(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapCustomerDtoToCustomer(customerDto);
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
        return CustomerMapper.mapCustomerToCustomerDto(customer.get());
    }

    public Long getCountOfRecords() {
        return customerRepository.count();
    }

    @Override
    public void increseCredit(CustomerDto customerDto, double amount) {
        double oldCredit = customerDto.getCredit();
        customerDto.setCredit(oldCredit + amount);
        update(customerDto);
    }

    @Override
    public void changePassword(CustomerDto customerDto, String oldPassword, String newPassword) {
        validation.validateUserRole(UserRole.CUSTOMER, customerDto.getUserRole());
        validation.validateCorrectPassword(oldPassword, customerDto.getPassword());
        validation.validatePassword(newPassword);
        customerDto.setPassword(newPassword);
        update(customerDto);
    }

    @Override
    public CustomerDto payForDoneOrder(OrderDto doneOrder) {
        assert doneOrder != null;
        double price = doneOrder.getFinalPrice();
        CustomerDto customerDto = doneOrder.getCustomer();
        validation.validateCustomerCredit(customerDto.getCredit(), price);
        ExpertDto expertDto = doneOrder.getExpert();
        customerDto.setCredit(customerDto.getCredit() - price);
        expertDto.setCredit(expertDto.getCredit() + price);
        doneOrder.setOrderStatus(OrderStatus.PAID);
        update(customerDto);
        expertService.update(expertDto);
        orderService.updateStatus(doneOrder);
        return customerDto;
    }
}
