package service;

import dao.ServiceDao;
import lombok.Data;
import model.services.Service;

/**
 * @author Negin Mousavi
 */
@Data
public class ServiceService {
    ServiceDao serviceDao;

    public Service getServiceById(int id) {
        Service service = serviceDao.read(id);
        if (service == null)
            throw new RuntimeException("service not found!");
        return service;
    }
}
