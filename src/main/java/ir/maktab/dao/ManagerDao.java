package ir.maktab.dao;

import ir.maktab.model.members.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Negin Mousavi
 */
@Repository
public interface ManagerDao extends JpaRepository<Manager, Integer> {

}
