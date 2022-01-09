package ir.maktab.service;

import ir.maktab.dao.ManagerDao;
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
public class ManagerService {
    private final ManagerDao managerDao;

    public void save(User manager) {
        managerDao.create(manager);
    }
}
