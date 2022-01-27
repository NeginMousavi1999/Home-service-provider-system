package ir.maktab.service;

import ir.maktab.data.dto.CustomerDto;
import ir.maktab.data.dto.PaymentDto;

/**
 * @author Negin Mousavi
 */
public interface PaymentService {
    CustomerDto pay(PaymentDto paymentDto);
}
