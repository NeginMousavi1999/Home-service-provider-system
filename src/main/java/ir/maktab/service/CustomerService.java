package ir.maktab.service;

import ir.maktab.data.entity.members.Customer;

/**
 * @author Negin Mousavi
 */
public interface CustomerService {
    void update(Customer customer);

    void save(Customer customer);

    Customer findByEmail(String email);

    Long getCountOfRecords();
}
