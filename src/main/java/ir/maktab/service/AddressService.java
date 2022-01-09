package ir.maktab.service;

import ir.maktab.dao.AddressDao;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Negin Mousavi
 */
@Data
@Service
public class AddressService {
    @Autowired
    AddressDao addressDao;
}
