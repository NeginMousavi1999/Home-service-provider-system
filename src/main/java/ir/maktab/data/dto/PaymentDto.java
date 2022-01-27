package ir.maktab.data.dto;

import ir.maktab.data.enumuration.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Negin Mousavi
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    private int identity;

    private PaymentMethod paymentMethod;

    private OrderDto order;

    private Date paymantDate;

    private String cardNumber;
}
