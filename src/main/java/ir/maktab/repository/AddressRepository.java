package ir.maktab.repository;


import ir.maktab.model.order.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Negin Mousavi
 */
@Repository
public interface AddressRepository extends CrudRepository<Address, Integer> {

}
