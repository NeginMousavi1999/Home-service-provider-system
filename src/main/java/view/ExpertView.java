package view;

import enumuration.UserRole;
import enumuration.UserStatus;
import lombok.Data;
import model.members.Expert;
import model.members.User;
import model.order.Order;
import model.order.Suggestion;
import org.apache.commons.io.IOUtils;
import service.ExpertService;
import service.ServiceService;
import service.SuggestionService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * @author Negin Mousavi
 */
@Data
public class ExpertView {
    private ExpertService expertService;
    private ServiceService serviceService;
    private SuggestionService suggestionService;

    public User createExpert(User expert) {
        String expertise = getExpertise("");
        String avatarNumber = getAvatarNumber("");
        String fileName = String.format("static-pictures/%s.png", avatarNumber);
        InputStream picStream = getStreamOfPicture(fileName);
        try {
            expert = Expert.builder()
                    .firstName(expert.getFirstName())
                    .lastName(expert.getLastName())
                    .email(expert.getEmail())
                    .password(expert.getPassword())
                    .expertise(expertise)
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

    private String getAvatarNumber(String avatar) {
        return avatar;//TODO
    }

    private String getExpertise(String expert) {
        try {
            serviceService.findServiceByName(expert);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return null;
        }
        return expert;
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
}
