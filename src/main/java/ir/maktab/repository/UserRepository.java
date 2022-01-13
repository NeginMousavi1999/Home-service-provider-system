package ir.maktab.repository;

import ir.maktab.model.members.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Negin Mousavi
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByEmailAndPassword(String email, String password);
}
