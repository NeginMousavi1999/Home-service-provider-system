package ir.maktab.service.implementation;

import ir.maktab.dao.ExpertDao;
import ir.maktab.exception.HomeServiceException;
import ir.maktab.model.members.Expert;
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
public class ExpertServiceImp implements ExpertService {
    private final ExpertDao expertDao;

    public void save(Expert expert) {
        expertDao.save(expert);
    }

    public boolean delete(Expert expert) {
        expertDao.delete(expert);
        return true;
    }

    public boolean update(Expert expert) {
        expertDao.save(expert);
        return true;
    }

    public Expert findByEmail(String email) {
        Optional<Expert> expert = expertDao.findByEmail(email);
        if (expert.isEmpty())
            throw new HomeServiceException("we have not this expert!");
        return expert.get();
    }

    public Long getCountOfRecords() {
        return expertDao.count();
    }
}
