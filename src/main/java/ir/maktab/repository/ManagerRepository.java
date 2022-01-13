package ir.maktab.repository;

import ir.maktab.model.members.Manager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Negin Mousavi
 */
@Repository
public interface ManagerRepository extends CrudRepository<Manager, Integer> {

}
