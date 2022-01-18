package ir.maktab.service.implementation;

import ir.maktab.data.dto.AddressDto;
import ir.maktab.data.repository.AddressRepository;
import ir.maktab.service.AddressService;
import ir.maktab.util.mapper.AddressMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Negin Mousavi
 */
@RequiredArgsConstructor
@Service
@Getter
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    @Override
    public void save(AddressDto addressDto) {
        addressRepository.save(AddressMapper.mapAddressDtoToAddressForSaving(addressDto));
    }
}
