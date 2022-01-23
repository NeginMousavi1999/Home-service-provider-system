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

    @GetMapping("/orders")
    public ModelAndView showOrdersToUpdateStatus(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("expert/update_order");
        HttpSession session = request.getSession();
        ExpertDto expertDto = (ExpertDto) session.getAttribute("expertDto");
        List<OrderDto> startingOrders = orderService.getOrdersToStartByExpert(expertDto);
        List<OrderDto> finishingOrders = orderService.getOrdersToFinishByExpert(expertDto);
        modelAndView.getModelMap().addAttribute("startingOrders", startingOrders)
                .addAttribute("finishingOrders", finishingOrders)
                .addAttribute("orderIdentityDto", new OrderIdentityDto());
        session.setAttribute("startingOrders", startingOrders);
        session.setAttribute("finishingOrders", finishingOrders);
        return modelAndView;
    }

    @PostMapping("/update_orders")
    public String updateStatus(HttpServletRequest request, @ModelAttribute OrderIdentityDto orderIdentityDto) {
        HttpSession session = request.getSession();
        List<OrderDto> startingOrders = (List<OrderDto>) session.getAttribute("startingOrders");
        List<OrderDto> finishingOrders = (List<OrderDto>) session.getAttribute("finishingOrders");
        int start = orderIdentityDto.getStart();
        int finish = orderIdentityDto.getFinish();
        if (start != Integer.MIN_VALUE)
            startingOrders.stream().filter(startedOrder -> startedOrder.getIdentity() == start)
                    .forEach(orderService::startOrder);

        if (finish != Integer.MIN_VALUE)
            finishingOrders.stream().filter(finishedOrder -> finishedOrder.getIdentity() == finish)
                    .forEach(orderService::finishOrder);

        return "redirect:/expert/orders";
    }
}
