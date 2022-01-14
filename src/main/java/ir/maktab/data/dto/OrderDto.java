package ir.maktab.data.dto;

import ir.maktab.data.enumuration.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Negin Mousavi
 */
@Data
@Builder
public class OrderDto {
    private Long identity;
    private SubServiceDto subService;
    private String description;
    private double finalPrice;
    private OrderStatus orderStatus;
    private Date registrationDate;
    private Date toBeDoneDate;
    private AddressDto address;
    private CustomerDto customer;
    private ExpertDto expert;
    private List<SuggestionDto> suggestions = new ArrayList<>();
}
