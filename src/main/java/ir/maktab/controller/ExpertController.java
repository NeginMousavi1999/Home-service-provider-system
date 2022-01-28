package ir.maktab.controller;

import ir.maktab.data.dto.*;
import ir.maktab.service.ExpertService;
import ir.maktab.service.OrderService;
import ir.maktab.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Negin Mousavi
 */
@Controller
@RequestMapping("/expert")
@RequiredArgsConstructor
public class ExpertController {
    private final ServiceService serviceService;
    private final ExpertService expertService;
    private final OrderService orderService;

    @RequestMapping("/dashboard")
    public String showDashboard() {
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
    public String addService(@ModelAttribute("dto") SystemServiceNameExpertDto serviceDto, HttpServletRequest request,
                             Model model) {
        try {
            ExpertDto expertDto = (ExpertDto) request.getSession().getAttribute("expertDto");
            List<ServiceDto> serviceDtos = new ArrayList<>();
            for (String name : serviceDto.getServicesName()) {
                serviceDtos.add(serviceService.findServiceByName(name));
            }
            expertService.addServices(expertDto, serviceDtos);
            serviceDto.setServicesName(serviceService.getAllServiceName());
            model.addAttribute("dto", serviceDto);
            model.addAttribute("succ_massage", "successfuly added");
            return "expert/add_services";
        } catch (Exception e) {
            serviceDto.setServicesName(serviceService.getAllServiceName());
            model.addAttribute("dto", serviceDto);
            model.addAttribute("error_massage", e.getLocalizedMessage());
            return "expert/add_services";
        }
    }

    @RequestMapping("/add_suggestion")
    public ModelAndView showAddSuggestion(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("expert/add_suggestion");
        HttpSession session = request.getSession();
        ExpertDto expertDto = (ExpertDto) session.getAttribute("expertDto");
        Set<ServiceDto> services = expertService.getServices(expertDto);
        expertDto.setServices(services);
        List<OrderDto> ordersReadyForSuggestion = orderService.getOrdersReadyForSuggestion(expertDto);
        session.setAttribute("ordersReadyForSuggestion", ordersReadyForSuggestion);
        modelAndView.getModelMap().addAttribute("list", ordersReadyForSuggestion);
        return modelAndView;
    }

    @PostMapping("/add_new_suggestion")
    public String addSuggestion(@RequestParam(name = "date") String date,
                                @RequestParam(name = "suggestedPrice") double suggestedPrice,
                                @RequestParam(name = "durationOfWork") int durationOfWork,
                                @RequestParam(name = "orderDtoIdentity") int orderDtoIdentity, Model model,
                                HttpServletRequest request) {
        HttpSession session = request.getSession();
        try {
            List<OrderDto> ordersReadyForSuggestion = (List<OrderDto>) session.getAttribute("ordersReadyForSuggestion");
            OrderDto orderDto = ordersReadyForSuggestion.stream().filter(dto -> dto.getIdentity() == orderDtoIdentity).findFirst().orElse(null);
            ExpertDto expertDto = (ExpertDto) session.getAttribute("expertDto");
            expertService.addNewSuggestion(date, suggestedPrice, durationOfWork, orderDto, expertDto);
            model.addAttribute("succ_massage", "successfuly added");
        } catch (Exception e) {
            model.addAttribute("error_massage", e.getLocalizedMessage());
        }
        return "expert/add_services";
    }

    @GetMapping("/show_tasks")
    public String showExpertTasks(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        ExpertDto expertDto = (ExpertDto) session.getAttribute("expertDto");
        List<SuggestionDto> suggestionsDto = expertService.getSuggestions(expertDto);
        session.setAttribute("expert_suggestions", suggestionsDto);
        model.addAttribute("expert_suggestions", suggestionsDto);
        return "expert/tasks";
    }

    @GetMapping("/start/{identity}")
    public String startOrder(@PathVariable("identity") int identity, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        try {
            List<SuggestionDto> suggestions = (List<SuggestionDto>) session.getAttribute("expert_suggestions");
            SuggestionDto suggestionDto = suggestions.stream().filter(dto -> dto.getIdentity() == identity).findFirst()
                    .orElse(null);
            assert suggestionDto != null;
            orderService.startOrder(suggestionDto.getOrder());
            model.addAttribute("succ_massage", "order started successfully");
        } catch (Exception exception) {
            model.addAttribute("error_massage", exception.getLocalizedMessage());
        }
        return "expert/tasks";
    }

    @GetMapping("/finish/{identity}")
    public String finishOrder(@PathVariable("identity") int identity, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        try {
            List<SuggestionDto> suggestions = (List<SuggestionDto>) session.getAttribute("expert_suggestions");
            SuggestionDto suggestionDto = suggestions.stream().filter(dto -> dto.getIdentity() == identity).findFirst()
                    .orElse(null);
            assert suggestionDto != null;
            orderService.finishOrder(suggestionDto.getOrder());
            model.addAttribute("succ_massage", "order finished successfully");
        } catch (Exception exception) {
            model.addAttribute("error_massage", exception.getLocalizedMessage());
        }
        return "expert/tasks";
    }

    @GetMapping("/show_feedback/{identity}")
    public String showCustomerFeedback(@PathVariable("identity") int identity, Model model) {
        //TODO
        return null;
    }
}
