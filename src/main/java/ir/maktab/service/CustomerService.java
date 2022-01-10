package ir.maktab.service;

import ir.maktab.dto.UserDto;
import ir.maktab.model.members.Customer;

/**
 * @author Negin Mousavi
 */
public interface CustomerService {
    void update(Customer customer);

    void save(Customer customer);

    Customer findByEmail(String email);

    Long getCountOfRecords();

    UserDto findByEmail2(String s);

    void update2(UserDto user);
}
