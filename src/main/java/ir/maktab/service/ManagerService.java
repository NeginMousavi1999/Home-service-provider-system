package ir.maktab.service;

import ir.maktab.dao.ManagerDao;
import ir.maktab.model.members.Manager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Negin Mousavi
 */
public interface ManagerService {
   void save(Manager manager);
}
