package ir.maktab.util.mapper;

import ir.maktab.data.dto.SubServiceDto;
import ir.maktab.data.entity.services.SubService;

/**
 * @author Negin Mousavi
 */
public class SubServiceMapper {
    private static final int suffix = 1000;

    public static SubService mapSubServiceDtoToSubService(SubServiceDto subServiceDto) {
        return SubService.builder()
                .id(subServiceDto.getIdentity() - suffix)
                .cost(subServiceDto.getCost())
                .description(subServiceDto.getDescription())
                .name(subServiceDto.getName())
                .build();
    }

/*    public static SubService mapSubServiceDtoToSubServiceToAddingExpert(SubServiceDto subServiceDto) {
        return SubService.builder()
                .id(subServiceDto.getIdentity() - suffix)
                .cost(subServiceDto.getCost())
                .description(subServiceDto.getDescription())
                .name(subServiceDto.getName())
                .service(ServiceMapper.mapServiceDtoToService(subServiceDto.getService()))
                .build();
    }*/

    public static SubService mapSubServiceDtoToSubServiceForSaving(SubServiceDto subServiceDto) {
        return SubService.builder()
                .cost(subServiceDto.getCost())
                .description(subServiceDto.getDescription())
                .name(subServiceDto.getName())
                .service(ServiceMapper.mapServiceDtoToService(subServiceDto.getService()))
                .build();
    }

    public static SubServiceDto mapSubServiceToSubServiceDto(SubService subService) {
        return SubServiceDto.builder()
                .identity(subService.getId() + suffix)
                .name(subService.getName())
                .cost(subService.getCost())
                .description(subService.getDescription())
                .build();
    }
}
