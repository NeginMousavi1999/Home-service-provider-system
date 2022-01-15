package ir.maktab.controller;

import ir.maktab.data.dto.LoginDto;
import ir.maktab.data.entity.members.Customer;
import ir.maktab.data.entity.members.Expert;
import ir.maktab.data.entity.members.User;
import ir.maktab.service.CustomerService;
import ir.maktab.service.ExpertService;
import ir.maktab.service.ManagerService;
import ir.maktab.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Negin Mousavi
 */
@Controller
@RequestMapping("/service")
@RequiredArgsConstructor
public class SystemController {

    private final UserService userService;
    private final CustomerService customerService;
    private final ExpertService expertService;
    private final ManagerService managerService;

    @RequestMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        modelAndView.getModelMap().addAttribute("loginData", new LoginDto());
        return modelAndView;
    }

    @RequestMapping("/doLogin")
    public String doLogin(@ModelAttribute("loginData") LoginDto loginDto, Model model) {
        User user;
        try {
            user = userService.findUserByUserNameAndPassword(loginDto);
            switch (user.getUserRole()) {
                case CUSTOMER:
                    Customer customer = customerService.findByEmail(user.getEmail());
                    model.addAttribute("customer", customer);
                    return "customer_dashboard";
                case EXPERT:
                    Expert expert = expertService.findByEmail(user.getEmail());
                    model.addAttribute("expert", expert);
                    return "expert_dashboard";
                default:
                    return "login";
            }
        } catch (Exception e) {
            model.addAttribute("massage", e.getLocalizedMessage());
            return "error";
        }
    }
    //TODO: register
}
