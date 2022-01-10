package ir.maktab.service;

import ir.maktab.dao.ServiceDao;
import ir.maktab.exception.HomeServiceException;
import ir.maktab.model.services.Service;
import ir.maktab.validation.Validation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Optional<Service> service = serviceDao.findById(id);
        if (service.isEmpty())
            throw new RuntimeException(" we have not service with this id");
        return service.get();
    }

    public List<String> getAllServiceName() {
        return serviceDao.findAll().stream().map(Service::getName).collect(Collectors.toList());
    }

    public boolean validateNewName(String name) {
        return validation.validateNewName(name, getAllServiceName());
    }

    public boolean addNewService(Service service) {
        serviceDao.save(service);
        return true;
    }

    public Service findServiceByName(String name) {
        Optional<Service> service = serviceDao.findByName(name);
        if (service.isEmpty())
            throw new HomeServiceException("we have n't this service!");
        return service.get();
    }
}
