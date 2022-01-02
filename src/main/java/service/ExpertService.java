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
}
