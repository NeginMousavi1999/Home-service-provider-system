package ir.maktab.dao;

import ir.maktab.model.members.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Negin Mousavi
 */
@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {

    Optional<Customer> findByEmail(String email);

}
