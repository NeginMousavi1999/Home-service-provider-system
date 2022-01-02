package view;

import enumuration.UserRole;
import enumuration.UserStatus;
import lombok.Data;
import model.members.Expert;
import model.members.User;
import org.apache.commons.io.IOUtils;
import service.ExpertService;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Negin Mousavi
 */
@Data
public class ExpertView {
    ExpertService expertService;

    public User createExpert(User expert) {
        String expertise = getExpertise();
        String avatarNumber = getAvatarNumber();
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

    private String getAvatarNumber() {
        return "2";//TODO
    }

    private String getExpertise() {
        return "";
    }

    public InputStream getStreamOfPicture(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        if (inputStream == null)
            throw new RuntimeException("unable to get resources");
        return inputStream;
    }
}
