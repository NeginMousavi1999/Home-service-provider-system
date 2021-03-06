package ir.maktab.data.repository;


import ir.maktab.data.entity.order.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Negin Mousavi
 */
@Repository
public interface AddressRepository extends CrudRepository<Address, Integer> {

}
