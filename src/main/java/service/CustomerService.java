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
        customerDao.update(user);
    }

    public void updateCredit(Customer customer) {
        customerDao.update(customer);
    }
}
