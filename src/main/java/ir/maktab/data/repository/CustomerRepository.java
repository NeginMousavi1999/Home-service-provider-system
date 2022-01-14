package ir.maktab.data.repository;

import ir.maktab.data.entity.members.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Negin Mousavi
 */
@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    Optional<Customer> findByEmail(String email);
}
