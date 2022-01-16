package ir.maktab.service;

import ir.maktab.data.dto.ServiceDto;

import java.util.List;

/**
 * @author Negin Mousavi
 */

public interface ServiceService {

    ServiceDto getServiceById(int id);

    List<String> getAllServiceName();

    boolean validateNewName(String name);

    boolean addNewService(ServiceDto service);

    ServiceDto findServiceByName(String name);
}
