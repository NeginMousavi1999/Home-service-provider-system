package ir.maktab.data.repository;

import ir.maktab.data.entity.members.Expert;
import ir.maktab.data.entity.services.Service;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Negin Mousavi
 */
@Repository
public interface ExpertRepository extends CrudRepository<Expert, Integer> {

    Optional<Expert> findByEmail(String email);

    @Query(value = "select e.services from Expert e where e.id=:id")
    Optional<List<Service>> customeGetServiceByExpertId(@Param("id") Long id);
}
