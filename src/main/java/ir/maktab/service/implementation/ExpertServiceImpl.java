package ir.maktab.service.implementation;

import ir.maktab.data.dto.ExpertDto;
import ir.maktab.data.entity.members.Expert;
import ir.maktab.data.repository.ExpertRepository;
import ir.maktab.exception.HomeServiceException;
import ir.maktab.service.ExpertService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
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
    ModelMapper modelMapper = new ModelMapper();

    public void save(ExpertDto expertDto) {
        Expert expert = modelMapper.map(expertDto, Expert.class);
        System.out.println(expert);
        expertRepository.save(expert);
    }

    public boolean delete(ExpertDto expertDto) {
        Expert expert = modelMapper.map(expertDto, Expert.class);
        expertRepository.delete(expert);
        return true;
    }

    public boolean update(ExpertDto expertDto) {
        Expert expert = modelMapper.map(expertDto, Expert.class);
        expertRepository.save(expert);
        return true;
    }

    public ExpertDto findByEmail(String email) {
        Optional<Expert> expert = expertRepository.findByEmail(email);
        if (expert.isEmpty())
            throw new HomeServiceException("we have not this expert!");
        modelMapper.addMappings(new PropertyMap<Expert, ExpertDto>() {
            @Override
            protected void configure() {
                skip(destination.getServices());
            }
        });
        return modelMapper.map(expert.get(), ExpertDto.class);
    }

    public Long getCountOfRecords() {
        return expertRepository.count();
    }
}
