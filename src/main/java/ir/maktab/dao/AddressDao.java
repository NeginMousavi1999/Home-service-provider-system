package ir.maktab.dao;


import ir.maktab.model.order.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Negin Mousavi
 */
@Repository
public interface AddressDao extends JpaRepository<Address, Integer> {

}
