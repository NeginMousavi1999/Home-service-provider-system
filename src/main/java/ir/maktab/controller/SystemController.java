package ir.maktab.controller;

import ir.maktab.data.dto.LoginDto;
import ir.maktab.data.entity.members.User;
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
            model.addAttribute("user", user);
            return "dashboard";
        } catch (Exception e) {
            model.addAttribute("massage", e.getLocalizedMessage());
            return "error";
        }
    }
}
