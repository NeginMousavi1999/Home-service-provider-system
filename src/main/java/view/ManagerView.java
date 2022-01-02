package view;

import enumuration.UserRole;
import enumuration.UserStatus;
import lombok.Data;
import model.members.Manager;
import model.members.User;
import service.ServiceService;

/**
 * @author Negin Mousavi
 */
@Data
public class ManagerView {
    ServiceService serviceService;

    public User createManager(User manager) {
        manager = Manager.builder()
                .firstName(manager.getFirstName())
                .lastName(manager.getLastName())
                .email(manager.getEmail())
                .password(manager.getPassword())

                .userStatus(UserStatus.WAITING)
                .userRole(UserRole.MANAGER)
                .build();
        return manager;
    }

    public boolean addNewService() {
        String name = "";

        try {
            serviceService.validateNewName(name);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
