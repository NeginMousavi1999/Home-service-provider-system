package service;

import dao.ManagerDao;
import lombok.Data;
import model.members.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Negin Mousavi
 */
@Data
@Service
public class ManagerService {
    @Autowired
    ManagerDao managerDao;

    public void save(User manager) {
        managerDao.create(manager);
    }
}
