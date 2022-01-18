package ir.maktab.service.implementation;

import ir.maktab.data.dto.ServiceDto;
import ir.maktab.data.entity.services.Service;
import ir.maktab.data.repository.ServiceRepository;
import ir.maktab.exception.HomeServiceException;
import ir.maktab.service.ServiceService;
import ir.maktab.util.mapper.ServiceMapper;
import ir.maktab.validation.Validation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Negin Mousavi
 */
@org.springframework.stereotype.Service
@RequiredArgsConstructor
@Getter
public class ServiceServiceImpl implements ServiceService {
    private final ServiceRepository serviceRepository;
    private final Validation validation;

    public ServiceDto getServiceById(int id) {
        Optional<Service> service = serviceRepository.findById(id);
        if (service.isEmpty())
            throw new RuntimeException(" we have not service with this id");
        return ServiceMapper.mapServiceToServiceDto(service.get());
    }

    public List<String> getAllServiceName() {
        return serviceRepository.findAll().stream().map(Service::getName).collect(Collectors.toList());
    }

    public boolean validateNewName(String name) {
        return validation.validateNewName(name, getAllServiceName());
    }

    public boolean addNewService(ServiceDto serviceDto) {
        serviceRepository.save(ServiceMapper.mapServiceDtoToService(serviceDto));
        return true;
    }

    public ServiceDto findServiceByName(String name) {
        Optional<Service> service = serviceRepository.findByName(name);
        if (service.isEmpty())
            throw new HomeServiceException("we have n't this service!");
        return ServiceMapper.mapServiceToServiceDto(service.get());
    }

    public Set<ServiceDto> getAllServiceIncludingSubService() {
        Optional<List<Service>> optionalServices = serviceRepository.getAllIncludeSubService();
        if (optionalServices.isEmpty())
            throw new HomeServiceException("no service yet!");
        return optionalServices.get().stream().map(ServiceMapper::mapServiceToServiceDtoIncludeSubService)
                .collect(Collectors.toSet());
    }
}
