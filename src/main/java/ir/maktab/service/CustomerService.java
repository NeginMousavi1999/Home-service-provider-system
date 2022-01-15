package ir.maktab.service;

import ir.maktab.data.dto.CustomerDto;
import ir.maktab.data.entity.members.Customer;

/**
 * @author Negin Mousavi
 */
public interface CustomerService {
    void update(Customer customer);

    void save(CustomerDto customer);

    CustomerDto findByEmail(String email);

    Long getCountOfRecords();
}
