package ir.maktab.service.implementation;

import ir.maktab.data.dto.ExpertDto;
import ir.maktab.data.entity.members.Expert;
import ir.maktab.data.repository.ExpertRepository;
import ir.maktab.exception.HomeServiceException;
import ir.maktab.service.ExpertService;
import ir.maktab.util.mapper.ExpertMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
}
