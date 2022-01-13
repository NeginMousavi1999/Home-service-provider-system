package ir.maktab.repository;

import ir.maktab.dto.UserRequestDto;
import ir.maktab.model.members.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Negin Mousavi
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer>, JpaSpecificationExecutor<User> {

    Optional<User> findByEmailAndPassword(String email, String password);

    static Specification<User> selectByConditions(UserRequestDto request) {
        return (Specification<User>) (root, cq, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (request.getFirstName() != null)
                predicateList.add(cb.equal(root.get("firstName"), request.getFirstName()));
            if (request.getLastName() != null)
                predicateList.add(cb.equal(root.get("lastName"), request.getLastName()));
            if (request.getEmail() != null)
                predicateList.add(cb.equal(root.get("email"), request.getEmail()));
            if (request.getUserRole() != null)
                predicateList.add(cb.equal(root.get("userRole"), request.getUserRole()));
            return cb.and(predicateList.toArray(new Predicate[0]));
        };
    }
}
