package service;

import dao.ManagerDao;
import lombok.Data;
import model.members.User;

/**
 * @author Negin Mousavi
 */
@Data
public class ManagerService {
    ManagerDao managerDao;

    public void save(User manager) {
        managerDao.create(manager);
    }
}
