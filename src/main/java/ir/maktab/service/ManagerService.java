package ir.maktab.service;

import ir.maktab.dao.ManagerDao;
import lombok.Data;
import ir.maktab.model.members.User;
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
