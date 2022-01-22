package ir.maktab.service;

import ir.maktab.data.dto.LoginDto;
import ir.maktab.data.dto.ManagerDto;
import ir.maktab.data.entity.members.Manager;

/**
 * @author Negin Mousavi
 */
public interface ManagerService {
    void save(Manager manager);

    ManagerDto findByUserNameAndPassword(LoginDto loginDto);

    void confirmUser(int identity);
}
