package ir.maktab.controller;

import ir.maktab.data.dto.*;
import ir.maktab.data.entity.services.Service;
import ir.maktab.service.ManagerService;
import ir.maktab.service.ServiceService;
import ir.maktab.service.SubServiceService;
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
    private final ServiceService serviceService;
    private final SubServiceService subServiceService;

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

    @GetMapping("/dashboard/add_service")
    public String showAddNewService(Model model) {
        model.addAttribute("service", new ServiceDto());
        return "add_service";
    }

    @PostMapping("/dashboard/add_new_service")
    public String addNewService(@ModelAttribute("service") ServiceDto serviceDto, Model model) {
        try {
            serviceService.validateNewName(serviceDto.getName());
            serviceService.addNewService(serviceDto);
        } catch (Exception e) {
            model.addAttribute("error_massage", e.getLocalizedMessage());
            return "add_service";
        }
        model.addAttribute("succ_massage", "successfuly added");
        return "add_service";
    }
}
