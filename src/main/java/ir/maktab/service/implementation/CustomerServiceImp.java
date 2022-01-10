package ir.maktab.service.implementation;

import ir.maktab.dao.CustomerDao;
import ir.maktab.exception.HomeServiceException;
import ir.maktab.model.members.Customer;
import ir.maktab.service.CustomerService;
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
public class CustomerServiceImp implements CustomerService {

    private final CustomerDao customerDao;

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

}
