package service;

import dao.ExpertDao;
import exception.HomeServiceException;
import lombok.Data;
import model.members.Expert;
import model.members.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Negin Mousavi
 */
@Data
@Service
public class ExpertService {
    @Autowired
    ExpertDao expertDao;

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
