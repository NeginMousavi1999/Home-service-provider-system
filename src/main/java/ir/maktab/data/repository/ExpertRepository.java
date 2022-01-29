package ir.maktab.data.repository;

import ir.maktab.data.entity.members.Expert;
import ir.maktab.data.entity.services.Service;
import ir.maktab.data.entity.services.SubService;
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

    @Query(value = "select e.subServices from Expert e where e.id=:id")
    Optional<List<SubService>> customeGetSubServiceByExpertId(@Param("id") Long id);
}
