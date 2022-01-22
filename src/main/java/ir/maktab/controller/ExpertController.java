package ir.maktab.controller;

import ir.maktab.data.dto.*;
import ir.maktab.data.enumuration.OrderStatus;
import ir.maktab.data.enumuration.SuggestionStatus;
import ir.maktab.data.enumuration.UserStatus;
import ir.maktab.service.ExpertService;
import ir.maktab.service.OrderService;
import ir.maktab.service.ServiceService;
import ir.maktab.service.SuggestionService;
import ir.maktab.util.GenerateDate;
import ir.maktab.validation.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    private final Validation validation;
    private final OrderService orderService;
    private final SuggestionService suggestionService;

    @RequestMapping("/dashboard")
    public String showDashboard(HttpServletRequest request, Model model) {
       // model.addAttribute("expert", request.getSession().getAttribute("expertDto"));
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
            Set<SuggestionDto> orderDtoSuggestions = suggestionService.getByOrder(orderDto);
            assert orderDto != null;
            orderDto.setSuggestions(orderDtoSuggestions);
            ExpertDto expertDto = (ExpertDto) session.getAttribute("expertDto");
            validation.validateUserStatus(UserStatus.CONFIRMED, expertDto.getUserStatus());
            orderDto.setSuggestions(orderDtoSuggestions);
            SuggestionDto suggestionDto = SuggestionDto.builder()
                    .expert(expertDto)
                    .order(orderDto)
                    .durationOfWork(durationOfWork)
                    .suggestedPrice(suggestedPrice)
                    .startTime(GenerateDate.generateByPattern("yyyy-MM-dd", date))
                    .suggestionStatus(SuggestionStatus.NEW)
                    .build();
            orderDto.setOrderStatus(OrderStatus.WAITING_FOR_SPECIALIST_SELECTION);
            orderDtoSuggestions.add(suggestionDto);
            orderDto.setSuggestions(orderDtoSuggestions);
            orderService.updateStatus(orderDto);
            suggestionService.saveSuggestion(suggestionDto);
            model.addAttribute("succ_massage", "successfuly added");
        } catch (Exception e) {
            model.addAttribute("error_massage", e.getLocalizedMessage());
        }
        return "expert/add_services";
    }
}
