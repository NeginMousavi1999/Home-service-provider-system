package dto;

import enumuration.OrderStatus;
import lombok.Data;
import model.members.Customer;
import model.order.Suggestion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Negin Mousavi
 */
@Data
public class OrderDto {
    private int id;
    private double suggestedPrice;
    private double finalPrice;
    private OrderStatus orderStatus;
    private Date registrationDate;
    private String address;
    private Customer customer;
    private List<Suggestion> suggestions = new ArrayList<>();
}
