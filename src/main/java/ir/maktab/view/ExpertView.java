package ir.maktab.view;

import ir.maktab.enumuration.OrderStatus;
import ir.maktab.enumuration.SuggestionStatus;
import ir.maktab.enumuration.UserRole;
import ir.maktab.enumuration.UserStatus;
import ir.maktab.model.members.Expert;
import ir.maktab.model.members.User;
import ir.maktab.model.order.Order;
import ir.maktab.model.order.Suggestion;
import ir.maktab.model.services.Service;
import ir.maktab.service.ExpertService;
import ir.maktab.service.OrderService;
import ir.maktab.service.ServiceService;
import ir.maktab.service.SuggestionService;
import ir.maktab.validation.Validation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author Negin Mousavi
 */
@RequiredArgsConstructor
@Component
@Getter
public class ExpertView {
    private final ExpertService expertService;
    private final ServiceService serviceService;
    private final SuggestionService suggestionService;
    private final OrderService orderService;
    private final Validation validation;

    public User createExpert(User expert, String serviceName, String avatarName) {
        String fileName = String.format("static-pictures/%s.png", avatarName);
        InputStream picStream = getStreamOfPicture(fileName);

        Service service = getService(serviceName);
        if (service == null)
            return null;
        HashSet<Service> services = new HashSet<>();
        services.add(service);
        try {
            expert = Expert.builder()
                    .firstName(expert.getFirstName())
                    .lastName(expert.getLastName())
                    .email(expert.getEmail())
                    .password(expert.getPassword())
                    .services(services)
                    .picture(IOUtils.toByteArray(picStream))
                    .score(0)
                    .userStatus(UserStatus.WAITING)
                    .userRole(UserRole.EXPERT)
                    .build();
        } catch (IOException | NullPointerException exception) {
            System.out.println(exception.getLocalizedMessage());
            return null;
        }

        expertService.save((Expert) expert);
        return expert;
    }

    private Service getService(String expert) {
        Service service;
        try {
            service = serviceService.findServiceByName(expert);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return null;
        }
        return service;
    }

    public InputStream getStreamOfPicture(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        if (inputStream == null)
            throw new RuntimeException("unable to get resources");
        return inputStream;
    }

    public void showPanel(User user) {

    }

    public List<Order> returnOrdersWithSameServiceExpert(Expert expert) {
        Set<Service> services = expert.getServices();
        List<Order> orderList = new ArrayList<>();
        services.forEach(service -> service.getSubServices().stream()
                .map(orderService::findBySubService).forEach(orderList::addAll));
        return orderList;
    }

    public Order chooseOrderForSendingSuggestion(List<Order> orderList, int index) {
        return orderList.get(index);
    }

    public void sendSuggestion(Expert expert, Order order, double price, int durationOfWork, Date startTime) {
        if (!(order.getOrderStatus().equals(OrderStatus.NEW) ||
                order.getOrderStatus().equals(OrderStatus.WAITING_FOR_SPECIALIST_SELECTION)))
            return;
        try {
            validation.validateUserStatus(UserStatus.CONFIRMED, expert.getUserStatus());
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return;
        }
        Suggestion suggestion = Suggestion.builder()
                .expert(expert)
                .order(order)
                .durationOfWork(durationOfWork)
                .startTime(startTime)
                .suggestedPrice(price)
                .suggestionStatus(SuggestionStatus.NEW)
                .build();
        order.setOrderStatus(OrderStatus.WAITING_FOR_SPECIALIST_SELECTION);
        order.getSuggestions().add(suggestion);
        orderService.update(order);
        suggestionService.saveSuggestion(suggestion);
    }

    public void finishOrder(Order order) {
        if (!order.getOrderStatus().equals(OrderStatus.STARTED))
            return;
        order.setOrderStatus(OrderStatus.DONE);
        orderService.update(order);
    }

    public void startOrder(Order order) {
        if (!order.getOrderStatus().equals(OrderStatus.WAITING_FOR_THE_SPECIALIST_TO_COME_TO_YOUR_PLACE))
            return;
        order.setOrderStatus(OrderStatus.STARTED);
        orderService.update(order);
    }

    public List<Suggestion> getExpertSuggestions(Expert expert) {
        return suggestionService.getAllSuggestions(expert);
    }

    public List<Suggestion> getAcceptedSuggestionsForStarting(Expert expert) {
        return suggestionService.getByStatus(expert, SuggestionStatus.ACCEPTED);
    }
}
