package ir.maktab.controller;

import ir.maktab.data.dto.*;
import ir.maktab.data.enumuration.OrderStatus;
import ir.maktab.data.enumuration.UserRole;
import ir.maktab.data.enumuration.UserStatus;
import ir.maktab.service.CustomerService;
import ir.maktab.service.OrderService;
import ir.maktab.service.ServiceService;
import ir.maktab.service.SubServiceService;
import ir.maktab.validation.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * @author Negin Mousavi
 */
@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final Validation validation;
    private final CustomerService customerService;
    private final ServiceService serviceService;
    private final SubServiceService subServiceService;
    private final OrderService orderService;

    @RequestMapping("/change_password")
    public String accessToChangePassword() {
        return "customer/customer_change_password";
    }

    @GetMapping("/update_password")
    public String changePassword(@RequestParam(name = "userName") String email,
                                 @RequestParam(name = "oldPass") String oldPassword,
                                 @RequestParam(name = "newPass") String newPassword, Model model) {

        System.out.println(email);
        System.out.println(oldPassword);
        System.out.println(newPassword);

        try {
            CustomerDto customerDto = customerService.findByEmail(email);
            validation.validateUserRole(UserRole.CUSTOMER, customerDto.getUserRole());
            validation.validateCorrectPassword(oldPassword, customerDto.getPassword());
            validation.validatePassword(newPassword);
            customerDto.setPassword(newPassword);
            customerService.update(customerDto);
            model.addAttribute("customer", customerDto);
            return "customer/customer_dashboard";
        } catch (Exception e) {
            model.addAttribute("massage", e.getLocalizedMessage());
            return "error";
        }
    }

    @RequestMapping("/add_order")
    public ModelAndView showAddNewOrder(ModelAndView modelAndView) {
        Set<ServiceDto> services = serviceService.getAllServiceIncludingSubService();
        modelAndView.setViewName("customer/add_order");
        modelAndView.getModelMap().addAttribute("set", services);
        modelAndView.getModelMap().addAttribute("order", new OrderRequestDto());
        return modelAndView;
    }

    @RequestMapping(value = "/add_new_order", method = RequestMethod.POST)
    public ModelAndView addNewOrder(@ModelAttribute("order") OrderRequestDto orderRequest, ModelAndView modelAndView,
                                    HttpServletRequest request) {
        try {
            CustomerDto customerDto = (CustomerDto) request.getSession().getAttribute("customerDto");
            validation.validateUserStatus(UserStatus.CONFIRMED, customerDto.getUserStatus());
            AddressDto addressDto = AddressDto.builder()
                    .country(orderRequest.getCountry())
                    .city(orderRequest.getCity())
                    .state(orderRequest.getState())
                    .postalCode(orderRequest.getPostalCode())
                    .build();
            SubServiceDto subServiceDto = subServiceService.findSubServiceByName(orderRequest.getSubServiceName());
            OrderDto orderDto = OrderDto.builder()
                    .address(addressDto)
                    .customer(customerDto)
                    .description(orderRequest.getDescription())
                    .orderStatus(OrderStatus.WAITING_FOR_SPECIALIST_SELECTION)
                    .subService(subServiceDto)
                    .build();
            orderService.saveOrder(orderDto);
            modelAndView.getModelMap().addAttribute("order", orderDto);
            modelAndView.getModelMap().addAttribute("succ_massage", "successfuly added");
            modelAndView.setViewName("customer/add_order");
            return showAddNewOrder(modelAndView);
        } catch (Exception e) {
            modelAndView.getModelMap().addAttribute("error_massage", e.getLocalizedMessage());
            modelAndView.setViewName("customer/add_order");
            return showAddNewOrder(modelAndView);
        }
    }
}
