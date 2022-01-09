package ir.maktab.dto;

import lombok.Data;
import ir.maktab.model.services.Service;

/**
 * @author Negin Mousavi
 */
@Data
public class SubServiceDto {
    private String name;
    private Service service;
    private double cost;
    private String description;
}
