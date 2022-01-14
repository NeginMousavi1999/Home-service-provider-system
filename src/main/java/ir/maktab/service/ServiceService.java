package ir.maktab.service;

import ir.maktab.data.entity.services.Service;

import java.util.List;

/**
 * @author Negin Mousavi
 */

public interface ServiceService {

    Service getServiceById(int id);

    List<String> getAllServiceName();

    boolean validateNewName(String name);

    boolean addNewService(Service service);

    Service findServiceByName(String name);
}
