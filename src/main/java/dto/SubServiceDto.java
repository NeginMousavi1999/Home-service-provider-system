package dto;

import lombok.Data;
import model.services.Service;

/**
 * @author Negin Mousavi
 */
@Data
public class SubServiceDto {
    private int id;
    private String name;
    private Service service;
    private double cost;
    private String description;
}
