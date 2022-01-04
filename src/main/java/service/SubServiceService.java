package service;

import dao.SubServiceDao;
import lombok.Data;
import model.services.SubService;
import validation.Validation;

import java.util.List;

/**
 * @author Negin Mousavi
 */
@Data
public class SubServiceService {
    SubServiceDao subServiceDao;
    Validation validation;

    public SubService getSubServiceById(int id) {
        SubService subService = subServiceDao.read(id);
        if (subService == null)
            throw new RuntimeException("sub service not found!");
        return subService;
    }

    public List<String> getAllServiceName() {
        return subServiceDao.getAllName();
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
            throw new RuntimeException("we have n't this sub service!");
        return subService;
    }
}
