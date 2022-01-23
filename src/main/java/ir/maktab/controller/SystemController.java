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
import ir.maktab.validation.Validation;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Negin Mousavi
 */
@Controller
@RequiredArgsConstructor
public class SystemController {

    private final UserService userService;
    private final CustomerService customerService;
    private final ExpertService expertService;
    private final ModelMapper modelMapper = new ModelMapper();
    private final Validation validation;

/*    @ExceptionHandler(value = BindException.class)
    public ModelAndView bindExceptionHandler(BindException bindException, HttpServletRequest request) {
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBIUTE);
        return new ModelAndView(lastView, bindException.getBindingResult().getModel());
    }*/

    @RequestMapping("/")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        modelAndView.getModelMap().addAttribute("loginData", new LoginDto());
        return modelAndView;
    }

    @PostMapping("/doLogin")
    public String doLogin(@ModelAttribute("loginData") LoginDto loginDto, Model model, HttpServletRequest request
            /*,BindingResult bindingResult*/) {
        /*if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach(error -> model.addAttribute(error.getDefaultMessage()));
            return "login";
        }*/
        User user;
        HttpSession session;
        try {
            user = userService.findUserByUserNameAndPassword(loginDto);
            switch (user.getUserRole()) {
                case CUSTOMER:
                    CustomerDto customerDto = customerService.findByEmail(user.getEmail());
                    session = request.getSession();
                    session.setAttribute("customerDto", customerDto);
                    return "redirect:/customer/dashboard";
                case EXPERT:
                    ExpertDto expertDto = expertService.findByEmail(user.getEmail());
                    session = request.getSession();
                    session.setAttribute("expertDto", expertDto);
                    return "redirect:/expert/dashboard";
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
                             Model model, HttpServletRequest request) {
        HttpSession session;
        try {
            validation.validatePassword(userDto.getPassword());
            validation.validateEmail(userDto.getEmail());
        } catch (Exception e) {
            model.addAttribute("massage", e.getLocalizedMessage());
            return "error";
        }

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
            session = request.getSession();
            session.setAttribute("expertDto", expertDto);
            return "expert/expert_dashboard";
        }

        CustomerDto customerDto;
        try {
            customerDto = modelMapper.map(userDto, CustomerDto.class);
            customerService.save(customerDto);
        } catch (Exception e) {
            model.addAttribute("massage", e.getLocalizedMessage());
            return "error";
        }
        session = request.getSession();
        session.setAttribute("customerDto", customerDto);
        return "customer/customer_dashboard";
    }
}
