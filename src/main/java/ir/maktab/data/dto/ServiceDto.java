package ir.maktab.data.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

/**
 * @author Negin Mousavi
 */
@Data
@Builder
public class ServiceDto {
    private Long identity;
    private String name;
    private Set<SubServiceDto> subServices;
}
