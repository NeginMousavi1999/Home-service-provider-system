package ir.maktab.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author Negin Mousavi
 */
@Data
@Builder
public class AddressDto {
    private String country;
    private String city;
    private String state;
    private String postalCode;
}
