package ir.maktab.dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;

/**
 * @author Negin Mousavi
 */
@Data
@Builder
public class AddressDto {
    private int id;
    private String country;
    private String city;
    private String state;
    private String postalCode;
}
