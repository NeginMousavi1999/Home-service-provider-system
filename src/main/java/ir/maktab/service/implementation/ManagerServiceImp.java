package ir.maktab.service.implementation;

import ir.maktab.model.members.Manager;
import ir.maktab.repository.ManagerRepository;
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
    private final ManagerRepository managerRepository;

    public void save(Manager manager) {
        managerRepository.save(manager);
    }
}
