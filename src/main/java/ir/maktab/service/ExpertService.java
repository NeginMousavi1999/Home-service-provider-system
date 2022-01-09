package ir.maktab.service;

import ir.maktab.dao.ExpertDao;
import ir.maktab.exception.HomeServiceException;
import ir.maktab.model.members.Expert;
import ir.maktab.model.members.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Negin Mousavi
 */
@RequiredArgsConstructor
@Service
@Getter
public class ExpertService {
    private final ExpertDao expertDao;

    public void save(User expert) {
        expertDao.create(expert);
    }

    public boolean delete(User expert) {
        expertDao.delete(expert);
        return true;
    }

    public boolean update(Expert expert) {
        expertDao.update(expert);
        return true;
    }

    public Expert findByEmail(String email) {
        Expert expert = expertDao.findByEmail(email);
        if (expert == null)
            throw new HomeServiceException("we have not this expert!");
        return expert;
    }

    public double getExpertScore(Expert expert) {
        return expertDao.getScore(expert);
    }
}
