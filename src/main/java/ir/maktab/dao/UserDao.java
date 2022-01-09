package ir.maktab.dao;

import ir.maktab.model.members.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Negin Mousavi
 */
@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    Optional<User> findByEmailAndPassword(String email, String password);

}
