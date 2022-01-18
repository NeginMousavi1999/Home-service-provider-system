package ir.maktab.data.dto;

import ir.maktab.data.enumuration.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author Negin Mousavi
 */
@Data
public class OrderRequestDto {
    private String subServiceName;
    private String description;
    private String country;
    private String city;
    private String state;
    private String postalCode;
}
