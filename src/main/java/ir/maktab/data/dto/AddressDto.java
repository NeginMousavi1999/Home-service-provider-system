package ir.maktab.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Negin Mousavi
 */
@Data
@Builder
public class AddressDto {
    private int identity;
    private String country;
    private String city;
    private String state;
    private String postalCode;
}
