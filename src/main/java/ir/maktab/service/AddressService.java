package ir.maktab.service;

import ir.maktab.dao.AddressDao;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Negin Mousavi
 */
@RequiredArgsConstructor
@Service
@Getter
public class AddressService {
    private final AddressDao addressDao;
}
