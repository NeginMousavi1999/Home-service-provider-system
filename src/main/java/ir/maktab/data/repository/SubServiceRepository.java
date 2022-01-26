package ir.maktab.data.repository;

import ir.maktab.data.entity.services.SubService;
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
