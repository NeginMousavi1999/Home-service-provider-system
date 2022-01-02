package service;

import dao.ServiceDao;
import lombok.Data;
import model.services.Service;
import validation.Validation;

import java.util.List;

/**
 * @author Negin Mousavi
 */
@Data
public class ServiceService {
    ServiceDao serviceDao;
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
}
