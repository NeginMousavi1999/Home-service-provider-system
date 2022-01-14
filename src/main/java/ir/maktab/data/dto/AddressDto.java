package ir.maktab.data.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author Negin Mousavi
 */
@Data
@Builder
public class AddressDto {
    private Long identity;
    private String country;
    private String city;
    private String state;
    private String postalCode;
}
