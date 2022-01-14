package ir.maktab.data.repository;

import ir.maktab.data.entity.members.Manager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Negin Mousavi
 */
@Repository
public interface ManagerRepository extends CrudRepository<Manager, Integer> {

}
