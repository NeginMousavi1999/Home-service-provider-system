package ir.maktab.service;

import ir.maktab.dao.ServiceDao;
import ir.maktab.exception.HomeServiceException;
import ir.maktab.model.services.Service;
import ir.maktab.validation.Validation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @author Negin Mousavi
 */
@org.springframework.stereotype.Service
@RequiredArgsConstructor
@Getter
public class ServiceService {
    private final ServiceDao serviceDao;
    private final Validation validation;

    public Service getServiceById(int id) {
        Service service = serviceDao.read(id);
        if (service == null)
            throw new RuntimeException("ir.maktab.service not found!");
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
            throw new HomeServiceException("we have n't this ir.maktab.service!");
        return service;
    }
}
