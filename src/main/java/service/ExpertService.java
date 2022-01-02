package service;

import dao.ExpertDao;
import model.members.Expert;

/**
 * @author Negin Mousavi
 */
public class ExpertService {
    ExpertDao expertDao;

    public void updateCredit(Expert expert) {
        expertDao.update(expert);
    }
}
