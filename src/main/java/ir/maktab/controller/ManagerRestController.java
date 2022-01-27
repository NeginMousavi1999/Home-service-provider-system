package ir.maktab.controller;

import ir.maktab.data.dto.OrderDto;
import ir.maktab.data.dto.OrdersHistoryDto;
import ir.maktab.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @author Negin Mousavi
 */
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class ManagerRestController {
    private final OrderService orderService;

    @ExceptionHandler(value = BindException.class)
    public ModelAndView bindExceptionHandler(BindException bindException, HttpServletRequest request) {
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBIUTE);
        return new ModelAndView(lastView, bindException.getBindingResult().getModel());
    }

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

    @PostMapping(value = "get_by_conditions")
    @ResponseBody
    public List<OrderDto> getAllOrdersByConditions(@Validated @RequestBody OrdersHistoryDto conditions) {
        return orderService.filteredOrders(conditions);
    }
}
