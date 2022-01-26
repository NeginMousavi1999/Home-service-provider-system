package ir.maktab.data.dto;

import lombok.Data;

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
