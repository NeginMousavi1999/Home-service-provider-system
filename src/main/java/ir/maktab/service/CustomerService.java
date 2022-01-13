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

    UserDto findByEmailTestForDto(String s);

    void updateTestForDto(UserDto user);
}
