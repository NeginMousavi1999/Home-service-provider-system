package ir.maktab.data.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.Set;

/**
 * @author Negin Mousavi
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class ExpertDto extends UserDto {
    /*private byte[] picture;*/
    private Set<ServiceDto> services;
    private double score;
}
