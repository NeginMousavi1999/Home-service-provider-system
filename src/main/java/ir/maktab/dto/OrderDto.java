package ir.maktab.dto;

import ir.maktab.enumuration.OrderStatus;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Negin Mousavi
 */
@Data
public class OrderDto {
    private double suggestedPrice;
    private double finalPrice;
    private OrderStatus orderStatus;
    private Date registrationDate;
    private String address;
    private UserDto customer;
    private List<SuggestionDto> suggestions = new ArrayList<>();
}
