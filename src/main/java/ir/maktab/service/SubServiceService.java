package ir.maktab.service;

import ir.maktab.data.entity.services.SubService;

import java.util.List;

/**
 * @author Negin Mousavi
 */

public interface SubServiceService {

    List<String> getAllServiceName();

    boolean validateNewName(String name);

    boolean addNewSubService(SubService subService);

    SubService findSubServiceByName(String name);
}
