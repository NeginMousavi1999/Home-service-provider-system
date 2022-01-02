package view;

import config.SpringConfig;
import enumuration.UserRole;
import enumuration.UserStatus;
import model.members.Expert;
import model.members.User;
import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import util.CreateScanner;
import validation.Validation;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * @author Negin Mousavi
 */
public class UserView {
    private final Scanner scanner = CreateScanner.getInstance();

    public void createUser() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        Validation validation = (Validation) context.getBean("validation");

        System.out.print("first name: ");
        String fName = scanner.nextLine();
        System.out.print("last name: ");
        String lName = scanner.nextLine();

        String email;
        while (true) {
            System.out.print("email: ");
            email = scanner.nextLine();
            try {
                validation.validateEmail(email);
                break;
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        }

        String password;
        while (true) {
            System.out.print("password: ");
            password = scanner.nextLine();
            try {
                validation.validatePassword(password);
                break;
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        }

        System.out.print("role(1.manager 2.customer 3.expert): ");
        String roleAnswer = scanner.nextLine();
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
        System.out.print("expertise: ");
        String expertise = scanner.nextLine();//TODO
        String fileName;
        whileLoop:
        while (true) {
            System.out.print("which avatar picture do you want for your profile?(1-10): ");
            String avatarNumber = scanner.nextLine();
            switch (avatarNumber) {
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                case "8":
                case "9":
                case "10":
                    fileName = String.format("static-pictures/%s.png", avatarNumber);
                    break whileLoop;
                default:
                    System.out.println("invalid input!");
            }
        }

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

    public InputStream getStreamOfPicture(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        if (inputStream == null)
            throw new RuntimeException("unable to get resources");
        return inputStream;
    }
}
