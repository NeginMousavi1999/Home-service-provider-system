package view;

import enumuration.OrderStatus;
import enumuration.SuggestionStatus;
import enumuration.UserRole;
import enumuration.UserStatus;
import lombok.Data;
import model.members.Expert;
import model.members.User;
import model.order.Order;
import model.order.Suggestion;
import model.services.Service;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.ExpertService;
import service.OrderService;
import service.ServiceService;
import service.SuggestionService;
import validation.Validation;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author Negin Mousavi
 */
@Data
@Component
public class ExpertView {
    @Autowired
    private ExpertService expertService;
    @Autowired
    private ServiceService serviceService;
    @Autowired
    private SuggestionService suggestionService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private Validation validation;

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

        expertService.save(expert);
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
                .map(subService -> orderService.findBySubService(subService)).forEach(orderList::addAll));
        return orderList;
    }

    public Order chooseOrderForSendingSuggestion(List<Order> orderList, int index) {
        return orderList.get(index);
    }

    public void sendSuggestion(Expert expert, Order order, double price, int durationOfWork, Date startTime) {
        if (!order.getOrderStatus().equals(OrderStatus.NEW))
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
