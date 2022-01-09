package ir.maktab.dao;

import ir.maktab.model.members.Expert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Negin Mousavi
 */
@Repository
public interface ExpertDao extends JpaRepository<Expert, Integer> {

    Optional<Expert> findByEmail(String email);

}
