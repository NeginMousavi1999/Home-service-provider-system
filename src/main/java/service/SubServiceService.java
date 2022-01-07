package service;

import dao.SubServiceDao;
import exception.HomeServiceException;
import lombok.Data;
import model.services.SubService;
import validation.Validation;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Negin Mousavi
 */
@Data
public class SubServiceService {
    SubServiceDao subServiceDao;
    Validation validation;

    public List<String> getAllServiceName() {
        return subServiceDao.returnAll().stream().map(SubService::getName).collect(Collectors.toList());
    }

    public boolean validateNewName(String name) {
        return validation.validateNewName(name, getAllServiceName());
    }

    public boolean addNewSubService(SubService subService) {
        subServiceDao.create(subService);
        return true;
    }

    public SubService findSubServiceByName(String name) {
        SubService subService = subServiceDao.findByName(name);
        if (subService == null)
            throw new HomeServiceException("we have n't this sub service!");
        return subService;
    }
}
