package ir.maktab.service;

import ir.maktab.data.dto.SubServiceDto;
import ir.maktab.data.dto.SubServiceRequestDto;

import java.util.List;

/**
 * @author Negin Mousavi
 */

public interface SubServiceService {

    List<String> getAllServiceName();

    boolean validateNewName(String name);

    boolean save(SubServiceDto subService);

    SubServiceDto findSubServiceByName(String name);

    void addNewSubService(SubServiceRequestDto subServiceRequestDto);
}
