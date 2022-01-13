package ir.maktab.service.implementation;

import ir.maktab.repository.AddressRepository;
import ir.maktab.service.AddressService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Negin Mousavi
 */
@RequiredArgsConstructor
@Service
@Getter
public class AddressServiceImp implements AddressService {
    private final AddressRepository addressRepository;
}
