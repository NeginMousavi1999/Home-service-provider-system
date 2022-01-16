package ir.maktab.controller;

import ir.maktab.data.dto.LoginDto;
import ir.maktab.data.dto.ManagerDto;
import ir.maktab.data.dto.UserDto;
import ir.maktab.data.dto.UserRequestDto;
import ir.maktab.service.ManagerService;
import ir.maktab.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author Negin Mousavi
 */
@Controller
@RequestMapping("/portal/admin")
@RequiredArgsConstructor
public class ManagerController {
    private final ManagerService managerService;
    private final UserService userService;

    @RequestMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        modelAndView.getModelMap().addAttribute("loginData", new LoginDto());
        return modelAndView;
    }

    @RequestMapping("/doLogin")
    public String doLogin(@ModelAttribute("loginData") LoginDto loginDto, Model model) {
        ManagerDto managerDto;
        try {
            managerDto = managerService.findByUserNameAndPassword(loginDto);
            model.addAttribute("managerDto", managerDto);
            return "manager_dashboard";
        } catch (Exception e) {
            model.addAttribute("massage", e.getLocalizedMessage());
            return "login";
        }
    }

    @GetMapping("/dashboard/search")
    public String search(Model model) {
        model.addAttribute("filterData", new UserRequestDto());
        return "search";
    }

    @PostMapping("/dashboard/doFilter")
    public String doFilter(@ModelAttribute("filterData") UserRequestDto userRequestDto, Model model) {
        List<UserDto> userDtos = userService.returnUsersFiltering(userRequestDto);
        model.addAttribute("users", userDtos);
        return "search";
    }
}
