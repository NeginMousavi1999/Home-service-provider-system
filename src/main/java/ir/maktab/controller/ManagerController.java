package ir.maktab.controller;

import ir.maktab.data.dto.*;
import ir.maktab.service.ManagerService;
import ir.maktab.service.ServiceService;
import ir.maktab.service.SubServiceService;
import ir.maktab.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
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

    @GetMapping("/dashboard")
    public String showDashboard() {
        return "/manager/manager_dashboard";
    }

    @RequestMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        modelAndView.getModelMap().addAttribute("loginData", new LoginDto());
        return modelAndView;
    }

    @ExceptionHandler(value = BindException.class)
    public ModelAndView bindExceptionHandler(BindException bindException, HttpServletRequest request) {
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBIUTE);
        return new ModelAndView(lastView, bindException.getBindingResult().getModel());
    }

    @RequestMapping("/doLogin")
    public String doLogin(@ModelAttribute("loginData") @Validated LoginDto loginDto, Model model) {
        ManagerDto managerDto;
        try {
            managerDto = managerService.findByUserNameAndPassword(loginDto);
            model.addAttribute("managerDto", managerDto);
            return "manager/manager_dashboard";
        } catch (Exception e) {
            model.addAttribute("massage", e.getLocalizedMessage());
            return "login";
        }
    }

    @GetMapping("/dashboard/search")
    public String search(Model model) {
        model.addAttribute("filterData", new UserRequestDto());
        return "manager/search";
    }

    @PostMapping("/dashboard/doFilter")
    public String doFilter(@ModelAttribute("filterData") UserRequestDto userRequestDto, Model model) {
        List<UserDto> userDtos = userService.returnUsersFiltering(userRequestDto);
        model.addAttribute("users", userDtos);
        return "manager/search";
    }

    @GetMapping("/dashboard/add_service")
    public String showAddNewService(Model model) {
        model.addAttribute("service", new ServiceDto());
        return "manager/add_service";
    }

    @PostMapping("/dashboard/add_new_service")
    public String addNewService(@ModelAttribute("service") ServiceDto serviceDto, Model model) {
        try {
            serviceService.addNewService(serviceDto);
        } catch (Exception e) {
            model.addAttribute("error_massage", e.getLocalizedMessage());
            return "manager/add_service";
        }
        model.addAttribute("succ_massage", "successfuly added");
        return "manager/add_service";
    }

    @GetMapping("/dashboard/add_subservice")
    public String showAddNewSubService(Model model) {
        List<String> serviceNameList = serviceService.getAllServiceName();
        model.addAttribute("subservice", new SubServiceRequestDto());
        model.addAttribute("list", serviceNameList);
        return "manager/add_subservice";
    }

    @PostMapping("/dashboard/add_new_subservice")
    public String addNewSubService(@ModelAttribute("subservice") SubServiceRequestDto subServiceRequestDto, Model model) {
        try {
            subServiceService.addNewSubService(subServiceRequestDto);
            model.addAttribute("succ_massage", "successfuly added");
        } catch (Exception e) {
            model.addAttribute("error_massage", e.getLocalizedMessage());
        }
        return showAddNewSubService(model);
    }

    @RequestMapping("/dashboard/confirm_user")
    public ModelAndView showUsersToConfirm() {
        ModelAndView modelAndView = new ModelAndView();
        List<UserDto> userDtos = userService.returnWaitingUsers();
        modelAndView.setViewName("manager/confirm_user");
        modelAndView.getModelMap().addAttribute("userDtos", userDtos)
                .addAttribute("identity_list", new UsersDto());
        return modelAndView;
    }

    @RequestMapping("/dashboard/confirm")
    public String confirm(@ModelAttribute("identity_list") UsersDto usersDto, Model model) {
        try {
            Arrays.stream(usersDto.getIdentities()).forEach(managerService::confirmUser);
            model.addAttribute("succ_massage", "confirmed successfuly");
            return "manager/confirm_user";
        } catch (Exception e) {
            model.addAttribute("error_massage", e.getLocalizedMessage());
            return "manager/confirm_user";
        }
    }
}
