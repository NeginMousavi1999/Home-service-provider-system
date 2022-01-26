package ir.maktab.data.dto;

import ir.maktab.data.enumuration.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

/**
 * @author Negin Mousavi
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private int identity;
    private SubServiceDto subService;
    private String description;
    private double finalPrice;
    private OrderStatus orderStatus;
    private Date registrationDate;
    private Date toBeDoneDate;
    private AddressDto address;
    private CustomerDto customer;
    private ExpertDto expert;
    private Set<SuggestionDto> suggestions;
}
