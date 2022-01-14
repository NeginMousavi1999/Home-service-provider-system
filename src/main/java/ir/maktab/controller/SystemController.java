package ir.maktab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Negin Mousavi
 */
@Controller
@RequestMapping("/service")
public class SystemController {
    @GetMapping("/login")
    public String profile() {
        return "login";
    }
}
