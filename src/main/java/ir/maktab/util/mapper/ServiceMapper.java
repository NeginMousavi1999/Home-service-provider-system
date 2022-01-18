package ir.maktab.util.mapper;

import ir.maktab.data.dto.ServiceDto;
import ir.maktab.data.dto.SubServiceDto;
import ir.maktab.data.entity.services.Service;
import ir.maktab.data.entity.services.SubService;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Negin Mousavi
 */
public class ServiceMapper {
    private static final int suffix = 1000;

    public static Service mapServiceDtoToService(ServiceDto serviceDto) {
        return Service.builder()
                .id(serviceDto.getIdentity() - suffix)
                .name(serviceDto.getName())
                .build();
    }

    public static ServiceDto mapServiceToServiceDto(Service service) {
        return ServiceDto.builder()
                .identity(service.getId() + suffix)
                .name(service.getName())
                .build();
    }

    public static ServiceDto mapServiceToServiceDtoIncludeSubService(Service service) {
        Set<SubServiceDto> subServiceDtos = service.getSubServices().stream()
                .map(SubServiceMapper::mapSubServiceToSubServiceDto).collect(Collectors.toSet());
        return ServiceDto.builder()
                .identity(service.getId() + suffix)
                .name(service.getName())
                .subServices(subServiceDtos)
                .build();
    }
}
