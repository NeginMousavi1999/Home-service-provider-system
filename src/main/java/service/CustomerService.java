package service;

import dao.CustomerDao;
import lombok.Data;
import model.members.Customer;
import model.members.User;

/**
 * @author Negin Mousavi
 */
@Data
public class CustomerService {
    private CustomerDao customerDao;

    public void changePassword(User user, String newPass) {
        user.setPassword(newPass);
        customerDao.update((Customer) user);
    }

    public void updateCredit(Customer customer) {
        customerDao.update(customer);
    }

    public void save(User customer) {
        customerDao.create(customer);
    }

    public Customer findByEmail(String email) {
        Customer customer = customerDao.findByEmail(email);
        if (customer == null)
            throw new RuntimeException("we have not customer with this email");
        return customer;
    }

    public Customer findById(int id) {
        return customerDao.findById(id);
    }
}
