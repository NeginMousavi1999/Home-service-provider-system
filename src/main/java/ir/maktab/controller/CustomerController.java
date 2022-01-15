package ir.maktab.controller;

import ir.maktab.data.dto.CustomerDto;
import ir.maktab.data.enumuration.UserRole;
import ir.maktab.service.CustomerService;
import ir.maktab.validation.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Negin Mousavi
 */
@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final Validation validation;
    private final CustomerService customerService;

    @RequestMapping("/change_password")
    public String accessToChangePassword() {
        return "customer_change_password";
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
            return "customer_dashboard";
        } catch (Exception e) {
            model.addAttribute("massage", e.getLocalizedMessage());
            return "error";
        }
    }
}
