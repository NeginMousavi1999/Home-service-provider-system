package ir.maktab.service.implementation;

import ir.maktab.dao.ManagerDao;
import ir.maktab.model.members.Manager;
import ir.maktab.service.ManagerService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Negin Mousavi
 */
@RequiredArgsConstructor
@Service
@Getter
public class ManagerServiceImp implements ManagerService {
    private final ManagerDao managerDao;

    public void save(Manager manager) {
        managerDao.save(manager);
    }
}
