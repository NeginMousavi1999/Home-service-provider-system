package view;

import enumuration.OrderStatus;
import enumuration.UserRole;
import enumuration.UserStatus;
import lombok.Data;
import model.members.Expert;
import model.members.User;
import model.order.Order;
import model.order.Suggestion;
import model.services.Service;
import org.apache.commons.io.IOUtils;
import service.ExpertService;
import service.OrderService;
import service.ServiceService;
import service.SuggestionService;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Negin Mousavi
 */
@Data
public class ExpertView {
    private ExpertService expertService;
    private ServiceService serviceService;
    private SuggestionService suggestionService;
    private OrderService orderService;

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
/*
                    .expertise(expertise)
*/
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

    public List<Order> returnOrdersWithSameExpertiseExpert(Expert expert) {
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
        Suggestion suggestion = Suggestion.builder()
                .expert(expert)
                .order(order)
                .durationOfWork(durationOfWork)
                .startTime(startTime)
                .suggestedPrice(price)
                .build();
        order.getSuggestions().add(suggestion);
        suggestionService.saveSuggestion(suggestion);
    }

    public void getDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = null;
        try {
            Date requestDate = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public void finishOrder(Order order) {
        order.setOrderStatus(OrderStatus.DONE);
        orderService.updateStatus(order);
    }

    public void startOrder(Order order) {
        order.setOrderStatus(OrderStatus.STARTED);
        orderService.updateStatus(order);
    }
}
