package ir.maktab.data.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author Negin Mousavi
 */
@Data
@Builder
public class SubServiceDto {
    private Long identity;
    private String name;
    private ServiceDto service;
    private double cost;
    private String description;
}
