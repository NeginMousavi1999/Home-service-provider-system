package ir.maktab.service.implementation;

import ir.maktab.data.dto.LoginDto;
import ir.maktab.data.dto.ManagerDto;
import ir.maktab.data.entity.members.Manager;
import ir.maktab.data.repository.ManagerRepository;
import ir.maktab.exception.HomeServiceException;
import ir.maktab.service.ManagerService;
import ir.maktab.util.mapper.ManagerMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Negin Mousavi
 */
@RequiredArgsConstructor
@Service
@Getter
public class ManagerServiceImpl implements ManagerService {
    private final ManagerRepository managerRepository;

    public void save(Manager manager) {
        managerRepository.save(manager);
    }

    public ManagerDto findByUserNameAndPassword(LoginDto loginDto) {
        Optional<Manager> manager = managerRepository.findByEmailAndPassword(loginDto.getUsername(), loginDto.getPassword());
        if (manager.isEmpty())
            throw new HomeServiceException("username or password is incorrect");
        return ManagerMapper.mapManagerToManagerDto(manager.get());
    }
}
