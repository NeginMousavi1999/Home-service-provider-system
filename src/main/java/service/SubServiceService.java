package service;

import dao.SubServiceDao;
import lombok.Data;
import model.services.SubService;

/**
 * @author Negin Mousavi
 */
@Data
public class SubServiceService {
    SubServiceDao subServiceDao;

    public SubService getSubServiceById(int id) {
        SubService subService = subServiceDao.read(id);
        if (subService == null)
            throw new RuntimeException("sub service not found!");
        return subService;
    }
}
