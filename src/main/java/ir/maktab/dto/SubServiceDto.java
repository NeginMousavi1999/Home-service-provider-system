package ir.maktab.dto;

import ir.maktab.model.services.Service;
import lombok.Data;

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
