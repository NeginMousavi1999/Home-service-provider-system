package ir.maktab.data.dto;

import lombok.Data;

/**
 * @author Negin Mousavi
 */
@Data
public class SubServiceRequestDto {
    private String name;
    private String serviceName;
    private double cost;
    private String description;
}
