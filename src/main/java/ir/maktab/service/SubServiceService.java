package ir.maktab.service;

import ir.maktab.data.dto.SubServiceDto;

import java.util.List;

/**
 * @author Negin Mousavi
 */

public interface SubServiceService {

    List<String> getAllServiceName();

    boolean validateNewName(String name);

    boolean addNewSubService(SubServiceDto subService);

    SubServiceDto findSubServiceByName(String name);
}
