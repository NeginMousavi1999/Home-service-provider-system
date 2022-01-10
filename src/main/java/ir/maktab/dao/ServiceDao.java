package ir.maktab.dao;

import ir.maktab.model.services.Service;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Negin Mousavi
 */
@Repository
public interface ServiceDao extends CrudRepository<Service, Integer> {

    List<Service> findAll();

    Optional<Service> findByName(String name);
}
