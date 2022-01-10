package ir.maktab.dao;

import ir.maktab.model.members.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Negin Mousavi
 */
@Repository
public interface UserDao extends CrudRepository<User, Integer> {

    Optional<User> findByEmailAndPassword(String email, String password);

}
