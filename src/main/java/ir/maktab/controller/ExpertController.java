package ir.maktab.controller;

import ir.maktab.data.dto.ExpertDto;
import ir.maktab.data.dto.ServiceDto;
import ir.maktab.data.dto.SystemServiceNameExpertDto;
import ir.maktab.service.ExpertService;
import ir.maktab.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Negin Mousavi
 */
@Controller
@RequestMapping("/expert")
@RequiredArgsConstructor
public class ExpertController {
    private final ServiceService serviceService;
    private final ExpertService expertService;

    @RequestMapping("/dashboard")
    public String showDashboard(HttpServletRequest request, Model model) {
        model.addAttribute("expert", request.getSession().getAttribute("expertDto"));
        return "expert/expert_dashboard";
    }

    @RequestMapping("/add_service")
    public ModelAndView showAddService() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("expert/add_services");
        SystemServiceNameExpertDto dto = new SystemServiceNameExpertDto();
        dto.setServicesName(serviceService.getAllServiceName());
        modelAndView.getModelMap().addAttribute("dto", dto);
        return modelAndView;
    }


    @PostMapping("/add_to_services")
    public String addService(@ModelAttribute("dto") SystemServiceNameExpertDto dto, HttpServletRequest request,
                             Model model) {
        try {
            ExpertDto expertDto = (ExpertDto) request.getSession().getAttribute("expertDto");
            List<ServiceDto> serviceDtos = new ArrayList<>();
            for (String name : dto.getServicesName()) {
                serviceDtos.add(serviceService.findServiceByName(name));
            }
            expertService.addServices(expertDto, serviceDtos);
            dto.setServicesName(serviceService.getAllServiceName());
            model.addAttribute("dto", dto);
            model.addAttribute("succ_massage", "successfuly added");
            return "expert/add_services";
        } catch (Exception e) {
            dto.setServicesName(serviceService.getAllServiceName());
            model.addAttribute("dto", dto);
            model.addAttribute("error_massage", e.getLocalizedMessage());
            return "expert/add_services";
        }
    }
}
