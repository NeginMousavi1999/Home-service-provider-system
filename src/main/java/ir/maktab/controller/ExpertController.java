package ir.maktab.controller;

import ir.maktab.data.dto.*;
import ir.maktab.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    private final SubServiceService subServiceService;
    private final ExpertService expertService;
    private final OrderService orderService;
    private final FeedbackService feedbackService;

    @RequestMapping("/dashboard")
    public String showDashboard() {
        return "expert/expert_dashboard";
    }

    @RequestMapping("/add_subservice")
    public String showAddSubService(HttpServletRequest request, Model model) {
        ExpertDto expertDto = (ExpertDto) request.getSession().getAttribute("expertDto");
        Set<ServiceDto> services = serviceService.getAllServiceIncludingSubService();
        Set<SubServiceDto> expertSubservices = expertService.getSubServices(expertDto);
        model.addAttribute("services", services)
                .addAttribute("expert_subservices", expertSubservices);
        return "expert/add_subservices";
    }

    @PostMapping("/add_to_services")
    public String addService(@RequestParam(name = "subservice_name") String subServiceName,
                             HttpServletRequest request, Model model) {
        try {
            ExpertDto expertDto = (ExpertDto) request.getSession().getAttribute("expertDto");
            SubServiceDto subServiceDto = subServiceService.findSubServiceByName(subServiceName);
            expertService.addSubServices(expertDto, subServiceDto);
            model.addAttribute("succ_massage", "successfuly added");
        } catch (Exception e) {
            model.addAttribute("error_massage", e.getLocalizedMessage());
        }
        return showAddSubService(request, model);
    }

    @GetMapping("/show_orders")
    public String showOrdersForSuggesting(HttpServletRequest request, Model model) {
        ExpertDto expertDto = (ExpertDto) request.getSession().getAttribute("expertDto");
        Set<SubServiceDto> subServices = expertService.getSubServices(expertDto);
        expertDto.setSubServiceDtos(subServices);
        List<OrderDto> ordersReadyForSuggestion = orderService.getOrdersReadyForSuggestion(expertDto);
        model.addAttribute("orders", ordersReadyForSuggestion);
        return "expert/show_orders";
    }

    @RequestMapping("/add_suggestion")
    public ModelAndView showAddNewSuggestion(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("expert/add_suggestion");
        HttpSession session = request.getSession();
        ExpertDto expertDto = (ExpertDto) session.getAttribute("expertDto");
        Set<SubServiceDto> services = expertService.getSubServices(expertDto);
        //expertDto.setServices(services); todo
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
        return "expert/add_subservices";
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
    public String showCustomerFeedback(@PathVariable("identity") int identity, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        List<SuggestionDto> suggestions = (List<SuggestionDto>) session.getAttribute("expert_suggestions");
        SuggestionDto suggestionDto = suggestions.stream().filter(dto -> dto.getIdentity() == identity).findFirst()
                .orElse(null);
        assert suggestionDto != null;
        FeedbackDto feedbackDto = feedbackService.getByExpertAndOrder(suggestionDto.getExpert(), suggestionDto.getOrder());
        model.addAttribute("feedback", feedbackDto);
        return "expert/show_feedback";
    }
}
