package service;

import dao.ServiceDao;
import exception.HomeServiceException;
import lombok.Data;
import model.services.Service;
import org.springframework.beans.factory.annotation.Autowired;
import validation.Validation;

import java.util.List;

/**
 * @author Negin Mousavi
 */
@Data
@org.springframework.stereotype.Service
public class ServiceService {
    @Autowired
    ServiceDao serviceDao;
    @Autowired
    Validation validation;

    public Service getServiceById(int id) {
        Service service = serviceDao.read(id);
        if (service == null)
            throw new RuntimeException("service not found!");
        return service;
    }

    public List<String> getAllServiceName() {
        return serviceDao.getAllName();
    }

    public boolean validateNewName(String name) {
        return validation.validateNewName(name, getAllServiceName());
    }

    public boolean addNewService(Service service) {
        serviceDao.create(service);
        return true;
    }

    public Service findServiceByName(String name) {
        Service service = serviceDao.findByName(name);
        if (service == null)
            throw new HomeServiceException("we have n't this service!");
        return service;
    }
}
