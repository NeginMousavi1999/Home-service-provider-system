package ir.maktab.controller;

import ir.maktab.data.dto.CustomerDto;
import ir.maktab.data.dto.ExpertDto;
import ir.maktab.data.dto.LoginDto;
import ir.maktab.data.dto.UserDto;
import ir.maktab.data.entity.members.User;
import ir.maktab.data.enumuration.UserRole;
import ir.maktab.data.enumuration.UserStatus;
import ir.maktab.service.CustomerService;
import ir.maktab.service.ExpertService;
import ir.maktab.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
    private final ModelMapper modelMapper = new ModelMapper();

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
                    CustomerDto customerDto = customerService.findByEmail(user.getEmail());
                    model.addAttribute("customer", customerDto);
                    return "customer_dashboard";
                case EXPERT:
                    ExpertDto expertDto = expertService.findByEmail(user.getEmail());
                    model.addAttribute("expert", expertDto);
                    return "expert_dashboard";
                default:
                    return "login";
            }
        } catch (Exception e) {
            model.addAttribute("massage", e.getLocalizedMessage());
            return "login";
        }
    }

    @RequestMapping("/register")
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        modelAndView.getModelMap().addAttribute("registerData", new UserDto());
        return modelAndView;
    }

    @PostMapping("/doRegister")
    public String doRegister(@RequestParam("file") MultipartFile file, @ModelAttribute("registerData") UserDto userDto,
                             Model model) {

        userDto.setUserStatus(UserStatus.WAITING);
        if (userDto.getUserRole().equals(UserRole.EXPERT)) {
            byte[] pictureBytes;
            ExpertDto expertDto;
            try {
                pictureBytes = file.getBytes();
                userDto.setPicture(pictureBytes);
                expertDto = modelMapper.map(userDto, ExpertDto.class);
                expertService.save(expertDto);
            } catch (Exception e) {
                model.addAttribute("massage", e.getLocalizedMessage());
                return "error";
            }
            model.addAttribute("expert", expertDto);
            return "expert_dashboard";
        }
        CustomerDto customerDto;
        try {
            customerDto = modelMapper.map(userDto, CustomerDto.class);
            customerService.save(customerDto);
        } catch (Exception e) {
            model.addAttribute("massage", e.getLocalizedMessage());
            return "error";
        }
        model.addAttribute("customer", customerDto);
        return "customer_dashboard";
    }
}
