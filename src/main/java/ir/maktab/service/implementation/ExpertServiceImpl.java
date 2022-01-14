package ir.maktab.service.implementation;

import ir.maktab.data.entity.members.Expert;
import ir.maktab.data.repository.ExpertRepository;
import ir.maktab.exception.HomeServiceException;
import ir.maktab.service.ExpertService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
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

    public void save(Expert expert) {
        expertRepository.save(expert);
    }

    public boolean delete(Expert expert) {
        expertRepository.delete(expert);
        return true;
    }

    public boolean update(Expert expert) {
        expertRepository.save(expert);
        return true;
    }

    public Expert findByEmail(String email) {
        Optional<Expert> expert = expertRepository.findByEmail(email);
        if (expert.isEmpty())
            throw new HomeServiceException("we have not this expert!");
        return expert.get();
    }

    public Long getCountOfRecords() {
        return expertRepository.count();
    }
}
