package view;

import enumuration.UserRole;
import enumuration.UserStatus;
import model.members.Expert;
import model.members.User;
import org.apache.commons.io.IOUtils;
import util.CreateScanner;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * @author Negin Mousavi
 */
public class UserView {
    private final Scanner scanner = CreateScanner.getInstance();

    public void createUser() {
        System.out.print("first name: ");
        String fName = scanner.nextLine();
        System.out.print("last name: ");
        String lName = scanner.nextLine();
        System.out.print("email: ");
        String email = scanner.nextLine(); //TODO: validate
        System.out.print("password: ");
        String password = scanner.nextLine(); //TODO: validate
        System.out.print("role(1.manager 2.customer 3.expert): ");
        String roleAnswer = scanner.nextLine();
        UserStatus userStatus;
        User user = User.builder()
                .firstName(fName)
                .lastName(lName)
                .email(email)
                .password(password)
                .userStatus(UserStatus.NEW)
                .build();

        switch (roleAnswer) {
            case "1":
                user = createManager(user);
                break;
            case "2":
                user = createCustomer(user);
                break;
            case "3":
                user = createExpert(user);
                break;
            default:
                System.out.println("invalid input!");
                return;
        }
        System.out.println(user);
    }

    private User createExpert(User expert) {
        String fileName = "static-pictures/pic.jpg"; //TODO : ask what picture user wants
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        if (inputStream == null)
            throw new RuntimeException("Unable to get resources");
        try {
            expert = Expert.builder()
                    .firstName(expert.getFirstName())
                    .lastName(expert.getLastName())
                    .email(expert.getEmail())
                    .password(expert.getPassword())
                    .expertise("")//TODO
                    .picture(IOUtils.toByteArray(inputStream))
                    .score(0)
                    .userStatus(UserStatus.WAITING)
                    .userRole(UserRole.EXPERT)
                    .build();
        } catch (IOException | NullPointerException exception) {
            exception.printStackTrace();
        }
        return expert;
    }

    private User createCustomer(User customer) {
        return customer;
    }

    private User createManager(User manager) {
        return manager;
    }
}
