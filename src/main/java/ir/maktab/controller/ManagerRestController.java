package ir.maktab.controller;

import ir.maktab.data.dto.OrderDto;
import ir.maktab.data.dto.OrdersHistoryDto;
import ir.maktab.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Negin Mousavi
 */
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class ManagerRestController {
    private final OrderService orderService;

    @GetMapping(value = "get_customers_services")
    @ResponseBody
    public List<OrderDto> getAllCustomersSystemService() {
        return orderService.getOrdersGivenByCustomer();
    }

    @GetMapping(value = "get_experts_services")
    @ResponseBody
    public List<OrderDto> getAllExpertsSystemService() {
        return orderService.getOrdersDoneByExpert();
    }

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
