package view;

import enumuration.UserRole;
import enumuration.UserStatus;
import lombok.Data;
import model.members.Manager;
import model.members.User;
import model.services.Service;
import model.services.SubService;
import service.ManagerService;
import service.ServiceService;
import service.SubServiceService;

/**
 * @author Negin Mousavi
 */
@Data
public class ManagerView {
    ServiceService serviceService;
    SubServiceService subServiceService;
    ManagerService managerService;

    public User createManager(User manager) {
        manager = Manager.builder()
                .firstName(manager.getFirstName())
                .lastName(manager.getLastName())
                .email(manager.getEmail())
                .password(manager.getPassword())

                .userStatus(UserStatus.WAITING)
                .userRole(UserRole.MANAGER)
                .build();

        managerService.save(manager);
        return manager;
    }

    public boolean addNewService(String name) {
        try {
            serviceService.validateNewName(name);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        Service service = Service.builder()
                .name(name)
                .build();
        return serviceService.addNewService(service);
    }

    public boolean addNewSubService(String name, String serviceName, double cost, String description) {
        try {
            subServiceService.validateNewName(name);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return false;
        }

        Service service;
        try {
            service = serviceService.findServiceByName(serviceName);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return false;
        }

        SubService subService = SubService.builder()
                .name(name)
                .service(service)
                .cost(cost)
                .description(description)
                .build();

        return subServiceService.addNewSubService(subService);
    }

    public void showPanel(User user) {

    }
}
