package ir.maktab.controller;

import ir.maktab.data.dto.*;
import ir.maktab.data.enumuration.OrderStatus;
import ir.maktab.data.enumuration.SuggestionStatus;
import ir.maktab.data.enumuration.UserRole;
import ir.maktab.data.enumuration.UserStatus;
import ir.maktab.service.*;
import ir.maktab.validation.Validation;
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
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final Validation validation;
    private final CustomerService customerService;
    private final ServiceService serviceService;
    private final SubServiceService subServiceService;
    private final OrderService orderService;
    private final SuggestionService suggestionService;
    private final ExpertService expertService;
    private final CommentService commentService;

    @GetMapping("/dashboard")
    public String showDashboard() {
        return "/customer/customer_dashboard";
    }

    @RequestMapping("/change_password")//todo
    public String accessToChangePassword() {
        return "customer/change_password";
    }

    @PostMapping("/update_password")
    public String changePassword(@RequestParam(name = "userName") String email,
                                 @RequestParam(name = "oldPass") String oldPassword,
                                 @RequestParam(name = "newPass") String newPassword, Model model) {
        try {
            CustomerDto customerDto = customerService.findByEmail(email);
            validation.validateUserRole(UserRole.CUSTOMER, customerDto.getUserRole());
            validation.validateCorrectPassword(oldPassword, customerDto.getPassword());
            validation.validatePassword(newPassword);
            customerDto.setPassword(newPassword);
            customerService.update(customerDto);
            model.addAttribute("customer", customerDto);
            model.addAttribute("succ_massage", "successfuly changed");
            return "customer/change_password";
        } catch (Exception e) {
            model.addAttribute("error_massage", e.getLocalizedMessage());
            return "customer/change_password";
        }
    }

    @RequestMapping("/add_order")
    public ModelAndView showAddNewOrder(ModelAndView modelAndView) {
        Set<ServiceDto> services = serviceService.getAllServiceIncludingSubService();
        modelAndView.setViewName("customer/add_order");
        modelAndView.getModelMap().addAttribute("set", services);
        modelAndView.getModelMap().addAttribute("order", new OrderRequestDto());
        return modelAndView;
    }

    @RequestMapping(value = "/add_new_order", method = RequestMethod.POST)
    public ModelAndView addNewOrder(@ModelAttribute("order") OrderRequestDto orderRequest, ModelAndView modelAndView,
                                    HttpServletRequest request) {
        try {
            CustomerDto customerDto = (CustomerDto) request.getSession().getAttribute("customerDto");
            validation.validateUserStatus(UserStatus.CONFIRMED, customerDto.getUserStatus());
            AddressDto addressDto = AddressDto.builder()
                    .country(orderRequest.getCountry())
                    .city(orderRequest.getCity())
                    .state(orderRequest.getState())
                    .postalCode(orderRequest.getPostalCode())
                    .build();
            SubServiceDto subServiceDto = subServiceService.findSubServiceByName(orderRequest.getSubServiceName());
            OrderDto orderDto = OrderDto.builder()
                    .address(addressDto)
                    .customer(customerDto)
                    .description(orderRequest.getDescription())
                    .orderStatus(OrderStatus.WAITING_FOR_SPECIALIST_SELECTION)
                    .subService(subServiceDto)
                    .build();
            orderService.saveOrder(orderDto);
            modelAndView.getModelMap().addAttribute("order", orderDto);
            modelAndView.getModelMap().addAttribute("succ_massage", "successfuly added");
            modelAndView.setViewName("customer/add_order");
            return showAddNewOrder(modelAndView);
        } catch (Exception e) {
            modelAndView.getModelMap().addAttribute("error_massage", e.getLocalizedMessage());
            modelAndView.setViewName("customer/add_order");
            return showAddNewOrder(modelAndView);
        }
    }

    @RequestMapping("/show_orders")
    public ModelAndView showOrders(ModelAndView modelAndView, HttpServletRequest request) {
        HttpSession session = request.getSession();
        CustomerDto customerDto = (CustomerDto) session.getAttribute("customerDto");
        List<OrderDto> orders = orderService.getOrdersByCustomerAndStatus(customerDto, OrderStatus.WAITING_FOR_SPECIALIST_SELECTION);
        modelAndView.setViewName("customer/show_orders");
        modelAndView.getModelMap().addAttribute("orders", orders);
        session.setAttribute("watingForSelectionOrders", orders);
        return modelAndView;
    }

    @RequestMapping("/show_suggestions")
    public ModelAndView showSuggestionsToChoose(@RequestParam("orderIdentity") int orderIdentity, ModelAndView modelAndView,
                                                HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<OrderDto> orders = (List<OrderDto>) session.getAttribute("watingForSelectionOrders");
        OrderDto selectedOrder = orders.stream().filter(dto -> dto.getIdentity() == orderIdentity).findFirst().orElse(null);
        List<SuggestionDto> suggestions = suggestionService.getSortedByOrder(selectedOrder);
        session.setAttribute("suggestions", suggestions);
        modelAndView.getModelMap().addAttribute("suggestions", suggestions);
        modelAndView.setViewName("customer/choose_suggestion");
        return modelAndView;
    }

    @RequestMapping(value = "/choose_suggestion", method = RequestMethod.POST)
    public ModelAndView ChooseSuggestion(@RequestParam("suggestionIdentity") int suggestionIdentity, ModelAndView modelAndView,
                                         HttpServletRequest request) {
        HttpSession session = request.getSession();
        try {
            List<SuggestionDto> suggestions = (List<SuggestionDto>) session.getAttribute("suggestions");
            SuggestionDto suggestion = null;
            for (SuggestionDto suggestionDto : suggestions) {
                if (suggestionDto.getIdentity() == suggestionIdentity) {
                    suggestion = suggestionDto;
                    suggestion.setSuggestionStatus(SuggestionStatus.ACCEPTED);
                } else
                    suggestionDto.setSuggestionStatus(SuggestionStatus.REJECTED);
            }
            assert suggestion != null;
            ExpertDto expert = suggestion.getExpert();
            OrderDto order = suggestion.getOrder();
            if (!order.getOrderStatus().equals(OrderStatus.WAITING_FOR_SPECIALIST_SELECTION)) {
                modelAndView.getModelMap().addAttribute("error_massage", "something is wrong!");
                modelAndView.setViewName("customer/choose_suggestion");
                return showOrders(modelAndView, request);
            }
            order.setExpert(expert);
            order.setFinalPrice(suggestion.getSuggestedPrice());
            order.setOrderStatus(OrderStatus.WAITING_FOR_THE_SPECIALIST_TO_COME_TO_YOUR_PLACE);
            order.setToBeDoneDate(suggestion.getStartTime());
            orderService.update(order);
            suggestions.forEach(suggestionService::update);
            modelAndView.getModelMap().addAttribute("succ_massage", "successfuly added");
            modelAndView.setViewName("customer/choose_suggestion");
            return showOrders(modelAndView, request);
        } catch (Exception e) {
            modelAndView.getModelMap().addAttribute("error_massage", e.getLocalizedMessage());
            modelAndView.setViewName("customer/choose_suggestion");
            return showOrders(modelAndView, request);
        }
    }

    @GetMapping("/pay")
    public String showOrdersToPay(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        CustomerDto customerDto = (CustomerDto) session.getAttribute("customerDto");
        List<OrderDto> orders = orderService.getOrdersByCustomerAndStatus(customerDto, OrderStatus.DONE);
        model.addAttribute("done_orders", orders);
        session.setAttribute("done_orders", orders);
        return "customer/pay_order";
    }

    @PostMapping("/paying")
    public String pay(@RequestParam("orderIdentity") int orderIdentity, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<OrderDto> doneOrders = (List<OrderDto>) session.getAttribute("done_orders");
        OrderDto doneOrder = doneOrders.stream().filter(order -> order.getIdentity() == orderIdentity).findFirst().orElse(null);
        try {
            assert doneOrder != null;
            double price = doneOrder.getFinalPrice();
            CustomerDto customerDto = doneOrder.getCustomer();
            validation.validateCustomerCredit(customerDto.getCredit(), price);
            ExpertDto expertDto = doneOrder.getExpert();
            customerDto.setCredit(customerDto.getCredit() - price);
            expertDto.setCredit(expertDto.getCredit() + price);
            doneOrder.setOrderStatus(OrderStatus.PAID);
            customerService.update(customerDto);
            expertService.update(expertDto);
            orderService.updateStatus(doneOrder);
            model.addAttribute("succ_massage", "successfuly paid");
        } catch (Exception exception) {
            model.addAttribute("error_massage", exception.getLocalizedMessage());
        }
        return showOrdersToPay(model, request);
    }

    @GetMapping("/order_feedback")
    public String showOrdersToFeedback(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        CustomerDto customerDto = (CustomerDto) session.getAttribute("customerDto");
        List<OrderDto> orders = orderService.getOrdersByCustomerAndStatus(customerDto, OrderStatus.PAID);
        model.addAttribute("paid_orders", orders);
        session.setAttribute("paid_orders", orders);
        return "customer/add_feedback";
    }

    @PostMapping("/feedback_page")
    public String showfeedbackPage(@RequestParam("orderIdentity") int orderIdentity, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<OrderDto> doneOrders = (List<OrderDto>) session.getAttribute("paid_orders");
        OrderDto choosenOrder = doneOrders.stream().filter(order -> order.getIdentity() == orderIdentity).findFirst().orElse(null);
        CustomerDto customerDto = (CustomerDto) session.getAttribute("customerDto");
        assert choosenOrder != null;
        CommentDto commentDto = CommentDto.builder()
                .order(choosenOrder)
                .customer(customerDto)
                .expert(choosenOrder.getExpert())
                .build();
        model.addAttribute("commentDto", commentDto);
        session.setAttribute("commentDto", commentDto);
        return "customer/feedback_page";
    }

    @PostMapping("/feedback")
    public String feedback(@ModelAttribute("commentDto") CommentDto commentDtoJsp, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        try {
            CommentDto commentDto = (CommentDto) session.getAttribute("commentDto");
            commentDto.setComment(commentDtoJsp.getComment());
            commentDto.setScore(commentDtoJsp.getScore());
            commentService.addComment(commentDto);
            model.addAttribute("succ_massage", "feedback add successfuly");
        } catch (Exception exception) {
            model.addAttribute("error_massage", exception.getLocalizedMessage());
        }
        return showOrdersToFeedback(model, request);
    }
}
