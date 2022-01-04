package view;

import enumuration.UserRole;
import enumuration.UserStatus;
import lombok.Data;
import model.members.Expert;
import model.members.User;
import model.services.Service;
import org.apache.commons.io.IOUtils;
import service.ExpertService;
import service.ServiceService;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Negin Mousavi
 */
@Data
public class ExpertView {
    ExpertService expertService;
    ServiceService serviceService;

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
}
