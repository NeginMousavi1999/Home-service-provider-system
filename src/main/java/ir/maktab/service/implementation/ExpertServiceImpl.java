package ir.maktab.service.implementation;

import ir.maktab.data.dto.ExpertDto;
import ir.maktab.data.dto.ServiceDto;
import ir.maktab.data.entity.members.Expert;
import ir.maktab.data.repository.ExpertRepository;
import ir.maktab.exception.HomeServiceException;
import ir.maktab.service.ExpertService;
import ir.maktab.util.mapper.ExpertMapper;
import ir.maktab.util.mapper.ServiceMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Negin Mousavi
 */
@RequiredArgsConstructor
@Service
@Getter
public class ExpertServiceImpl implements ExpertService {
    private final ExpertRepository expertRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public void save(ExpertDto expertDto) {
        Expert expert = modelMapper.map(expertDto, Expert.class);
        System.out.println(expert);
        expertRepository.save(expert);
    }

    public boolean delete(ExpertDto expertDto) {
        Expert expert = ExpertMapper.mapExpertDtoToExpert(expertDto);
        expertRepository.delete(expert);
        return true;
    }

    public boolean update(ExpertDto expertDto) {
        Expert expert = ExpertMapper.mapExpertDtoToExpert(expertDto);
        expertRepository.save(expert);
        return true;
    }

    public ExpertDto findByEmail(String email) {
        Optional<Expert> expert = expertRepository.findByEmail(email);
        if (expert.isEmpty())
            throw new HomeServiceException("we have not this expert!");
        return ExpertMapper.mapExpertToExpertDto(expert.get());
    }

    public Long getCountOfRecords() {
        return expertRepository.count();
    }

    public void addServices(ExpertDto expertDto, List<ServiceDto> serviceDtos) {
        Expert expert = ExpertMapper.mapExpertDtoToExpert(expertDto);
        Optional<List<ir.maktab.data.entity.services.Service>> optionalServices = expertRepository
                .customeGetServiceByExpertId(expert.getId());
        List<ir.maktab.data.entity.services.Service> list;
        list = optionalServices.orElseGet(ArrayList::new);
        serviceDtos.stream().map(ServiceMapper::mapServiceDtoToService)
                .filter(service -> !list.contains(service)).forEach(list::add);
        expert.setServices(new HashSet<>(list));
        expertRepository.save(expert);
    }

    @Override
    public Set<ServiceDto> getServices(ExpertDto expertDto) {
        Optional<List<ir.maktab.data.entity.services.Service>> services = expertRepository
                .customeGetServiceByExpertId(ExpertMapper.mapExpertDtoToExpert(expertDto).getId());
        if (services.isEmpty())
            throw new HomeServiceException("no services!");
        return services.get().stream().map(ServiceMapper::mapServiceToServiceDto).collect(Collectors.toSet());
    }

    @Override
    public void updateScore(ExpertDto expertDto, double score) {
        double oldScore = expertDto.getScore();
        expertDto.setScore((oldScore + score) / 2);
        update(expertDto);
    }
}
