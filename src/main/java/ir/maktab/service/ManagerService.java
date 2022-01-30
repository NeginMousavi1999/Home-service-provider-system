package ir.maktab.service;

import ir.maktab.data.dto.ExpertDto;
import ir.maktab.data.dto.LoginDto;
import ir.maktab.data.dto.ManagerDto;
import ir.maktab.data.dto.SubServiceDto;
import ir.maktab.data.entity.members.Manager;

import java.util.Map;
import java.util.Set;

/**
 * @author Negin Mousavi
 */
public interface ManagerService {
    void save(Manager manager);

    ManagerDto findByUserNameAndPassword(LoginDto loginDto);

    void confirmUser(int identity);

    Map<ExpertDto, Set<SubServiceDto>> getExpertAndSubServices(int identity);

    void addSubServices(ExpertDto expertDto, SubServiceDto subServiceDto);
}
