package ir.maktab.dto;

import lombok.Data;

/**
 * @author Negin Mousavi
 */
@Data
public class SubServiceDto {
    private String name;
    private ServiceDto service;
    private double cost;
    private String description;
}
