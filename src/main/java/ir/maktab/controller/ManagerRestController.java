package ir.maktab.controller;

import ir.maktab.data.dto.OrderDto;
import ir.maktab.data.dto.OrdersHistoryDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Negin Mousavi
 */
@RestController
@RequestMapping("/admin")
public class ManagerRestController {

    @PostMapping(value = "/all")
    @ResponseBody
    public List<OrderDto> getAllOrders(@RequestBody OrdersHistoryDto ordersFilter) {
        List<OrderDto> orderDtoList = new ArrayList<>();
        OrderDto orderDto = OrderDto.builder()
                .identity(1)
                .finalPrice(20)
                .build();
        orderDtoList.add(orderDto);
        return orderDtoList;
    }
}
