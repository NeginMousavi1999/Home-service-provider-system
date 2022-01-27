package ir.maktab.controller;

import ir.maktab.data.dto.*;
import ir.maktab.data.enumuration.OrderStatus;
import ir.maktab.data.enumuration.PaymentMethod;
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
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final ServiceService serviceService;
    private final OrderService orderService;
    private final SuggestionService suggestionService;
    private final CommentService commentService;
    private final PaymentService paymantService;

    @GetMapping("/dashboard")
    public String showDashboard() {
        return "/customer/customer_dashboard";
    }

    @RequestMapping("/change_password")
    public String accessToChangePassword() {
        return "customer/change_password";
    }

    @PostMapping("/update_password")
    public String changePassword(@RequestParam(name = "userName") String email,
                                 @RequestParam(name = "oldPass") String oldPassword,
                                 @RequestParam(name = "newPass") String newPassword, Model model) {
        try {
            CustomerDto customerDto = customerService.findByEmail(email);
            customerService.changePassword(customerDto, oldPassword, newPassword);
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
            OrderDto orderDto = orderService.addNewOrder(orderRequest, customerDto);
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
            suggestionService.chooseSuggestion(suggestionIdentity, suggestions);
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

    @GetMapping("/paying_from_credit/{identity}")
    public String payFromCredit(@PathVariable("identity") int identity, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        OrderDto paymentOrder = getPaymentOrderDto(identity, session);
        PaymentDto paymentDto = PaymentDto.builder()
                .order(paymentOrder)
                .paymentMethod(PaymentMethod.CREDIT)
                .build();
        try {
            CustomerDto customerDto = paymantService.pay(paymentDto);
            session.setAttribute("customerDto", customerDto);
            model.addAttribute("succ_massage", "successfuly paid");
        } catch (Exception exception) {
            model.addAttribute("error_massage", exception.getLocalizedMessage());
        }
        return showOrdersToPay(model, request);
    }

    @GetMapping("/paying_online/{identity}")
    public String showOnlinePayPage(@PathVariable("identity") int identity, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        OrderDto paymentOrder = getPaymentOrderDto(identity, session);
        PaymentDto paymentDto = PaymentDto.builder()
                .order(paymentOrder)
                .paymentMethod(PaymentMethod.ONLINE)
                .build();
        model.addAttribute("paymentDto", paymentDto);
        session.setAttribute("paymentDto", paymentDto);
        return "customer/pay_online";
    }

    private OrderDto getPaymentOrderDto(int identity, HttpSession session) {
        List<OrderDto> doneOrders = (List<OrderDto>) session.getAttribute("done_orders");
        return doneOrders.stream().filter(order -> order.getIdentity() == identity).findFirst().orElse(null);
    }

    @PostMapping("/pay_online")
    public String doOnlinePay(@ModelAttribute("paymentDto") PaymentDto donePaymentDto, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        try {
            PaymentDto paymentDto = (PaymentDto) session.getAttribute("paymentDto");
            paymentDto.setCardNumber(donePaymentDto.getCardNumber());
            CustomerDto customerDto = paymantService.pay(paymentDto);
            session.setAttribute("customerDto", customerDto);
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
            commentService.addComment(commentDto, commentDtoJsp.getScore(), commentDtoJsp.getComment());
            model.addAttribute("succ_massage", "feedback add successfuly");
        } catch (Exception exception) {
            model.addAttribute("error_massage", exception.getLocalizedMessage());
        }
        return showOrdersToFeedback(model, request);
    }

    @GetMapping("/bank")
    public String showincreasePage() {
        return "customer/increase_credit";
    }

    @PostMapping("/increase_credit")
    public String increaseCredit(@RequestParam(value = "amount") String stringAmount, HttpServletRequest request,
                                 Model model) {
        HttpSession session = request.getSession();
        try {
            CustomerDto customerDto = (CustomerDto) session.getAttribute("customerDto");
            double amount = Double.parseDouble(stringAmount);
            customerService.increseCredit(customerDto, amount);
            model.addAttribute("succ_massage", "successfuly increased");
        } catch (Exception exception) {
            model.addAttribute("error_massage", exception.getLocalizedMessage());
        }
        return "redirect:/customer/bank";
    }
}
