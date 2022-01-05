package service;

import dao.ExpertDao;
import lombok.Data;
import model.members.Expert;
import model.members.User;

/**
 * @author Negin Mousavi
 */
@Data
public class ExpertService {
    ExpertDao expertDao;

    public void updateCredit(Expert expert) {
        expertDao.update(expert);
    }

    public void save(User expert) {
        expertDao.create(expert);
    }

    public boolean delete(User expert) {
        expertDao.delete(expert);
        return true;
    }

/*    public User findExpertByEmail(String email) {
        User expert = expertDao.findByEmail(email);
        if (expert == null)
            throw new RuntimeException("we have not this expert!");
        return expert;
    }*/

    public boolean updateExpert(Expert expert) {
        expertDao.update(expert);
        return true;
    }

    public Expert findById(int id) {
        return expertDao.findById(id);
    }

    public Expert findByEmail(String email) {
        Expert expert = expertDao.findByEmail(email);
        if (expert == null)
            throw new RuntimeException("we have not this expert!");
        return expert;
    }
}
