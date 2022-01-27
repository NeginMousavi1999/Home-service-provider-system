package ir.maktab.data.dto;

import lombok.Data;

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
