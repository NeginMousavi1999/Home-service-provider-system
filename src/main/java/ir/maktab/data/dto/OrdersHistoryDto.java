package ir.maktab.data.dto;

import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.Pattern;

/**
 * @author Negin Mousavi
 */
@Data
public class OrdersHistoryDto {

    private String fromDate;

    private String toDate;

    private String status;

    private String service;

    private String subService;

}
