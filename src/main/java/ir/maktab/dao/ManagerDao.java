package ir.maktab.dao;

import ir.maktab.model.members.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Negin Mousavi
 */
@Repository
public interface ManagerDao extends CrudRepository<Manager, Integer> {

}
