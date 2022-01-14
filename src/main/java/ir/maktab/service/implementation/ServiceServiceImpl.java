package ir.maktab.service.implementation;

import ir.maktab.data.entity.services.Service;
import ir.maktab.data.repository.ServiceRepository;
import ir.maktab.exception.HomeServiceException;
import ir.maktab.service.ServiceService;
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
public class ServiceServiceImpl implements ServiceService {//todo: impl
    private final ServiceRepository serviceRepository;
    private final Validation validation;

    public Service getServiceById(int id) {
        Optional<Service> service = serviceRepository.findById(id);
        if (service.isEmpty())
            throw new RuntimeException(" we have not service with this id");
        return service.get();
    }

    public List<String> getAllServiceName() {
        return serviceRepository.findAll().stream().map(Service::getName).collect(Collectors.toList());
    }

    public boolean validateNewName(String name) {
        return validation.validateNewName(name, getAllServiceName());
    }

    public boolean addNewService(Service service) {//todo: inputs and outputs are dto
        serviceRepository.save(service);
        return true;
    }

    public Service findServiceByName(String name) {
        Optional<Service> service = serviceRepository.findByName(name);
        if (service.isEmpty())
            throw new HomeServiceException("we have n't this service!");
        return service.get();
    }
}
