package ir.maktab.service;

import ir.maktab.data.dto.CustomerDto;
import ir.maktab.data.dto.OrderDto;

/**
 * @author Negin Mousavi
 */
public interface CustomerService {
    void update(CustomerDto customer);

    void save(CustomerDto customer);

    CustomerDto findByEmail(String email);

    Long getCountOfRecords();

    void increseCredit(CustomerDto customerDto, double amount);

    void changePassword(CustomerDto customerDto, String oldPassword, String newPassword);

    CustomerDto payForDoneOrder(OrderDto doneOrder);
}
