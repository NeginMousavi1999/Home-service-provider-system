package ir.maktab.service;

import ir.maktab.dao.ManagerDao;
import ir.maktab.model.members.Manager;
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

    public void save(Manager manager) {
        managerDao.save(manager);
    }
}
