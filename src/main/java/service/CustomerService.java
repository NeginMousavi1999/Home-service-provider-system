package service;

import dao.CustomerDao;
import exception.HomeServiceException;
import lombok.Data;
import model.members.Customer;
import model.members.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Negin Mousavi
 */
@Data
@Service
public class CustomerService {
    @Autowired
    private CustomerDao customerDao;

    public void update(Customer customer) {
        customerDao.update(customer);
    }

    public void save(User customer) {
        customerDao.create(customer);
    }

    public Customer findByEmail(String email) {
        Customer customer = customerDao.findByEmail(email);
        if (customer == null)
            throw new HomeServiceException("we have not customer with this email");
        return customer;
    }
}
