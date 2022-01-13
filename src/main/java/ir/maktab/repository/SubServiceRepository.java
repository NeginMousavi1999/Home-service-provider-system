package ir.maktab.repository;

import ir.maktab.model.services.SubService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Negin Mousavi
 */
@Repository
public interface SubServiceRepository extends JpaRepository<SubService, Integer> {

    Optional<SubService> findByName(String name);
}
