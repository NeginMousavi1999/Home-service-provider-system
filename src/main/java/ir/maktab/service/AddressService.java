package ir.maktab.service;

import ir.maktab.data.dto.AddressDto;

/**
 * @author Negin Mousavi
 */
public interface AddressService {
    void save(AddressDto addressDto);

    AddressDto findAddress(String country, String city, String state, String postalCode);
}
