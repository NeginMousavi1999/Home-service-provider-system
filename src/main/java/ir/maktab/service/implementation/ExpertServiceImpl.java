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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

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
        Optional<List<ir.maktab.data.entity.services.Service>> optionalServices = expertRepository.customeGetServiceByExpertId(expert.getId());
        List<ir.maktab.data.entity.services.Service> list;
        list = optionalServices.orElseGet(ArrayList::new);
        for (ServiceDto serviceDto : serviceDtos) {
            ir.maktab.data.entity.services.Service service = ServiceMapper.mapServiceDtoToService(serviceDto);
            if (list.contains(service))
                continue;
            list.add(service);
        }
        expert.setServices(new HashSet<>(list));
        expertRepository.save(expert);
    }

}
