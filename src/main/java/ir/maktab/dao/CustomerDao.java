package ir.maktab.dao;

import ir.maktab.model.members.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Negin Mousavi
 */
@Repository
public interface CustomerDao extends CrudRepository<Customer, Integer> {

    Optional<Customer> findByEmail(String email);

}
