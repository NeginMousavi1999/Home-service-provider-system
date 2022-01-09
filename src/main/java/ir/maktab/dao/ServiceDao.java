package ir.maktab.dao;

import ir.maktab.model.services.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Negin Mousavi
 */
@Repository
public interface ServiceDao extends JpaRepository<Service, Integer> {

    List<Service> findAll();

    Optional<Service> findByName(String name);
}
