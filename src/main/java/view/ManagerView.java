package view;

import enumuration.UserRole;
import enumuration.UserStatus;
import lombok.Data;
import model.members.Customer;
import model.members.Expert;
import model.members.Manager;
import model.members.User;
import model.services.Service;
import model.services.SubService;
import service.*;
import validation.Validation;

/**
 * @author Negin Mousavi
 */
@Data
public class ManagerView {
    private ServiceService serviceService;
    private SubServiceService subServiceService;
    private ManagerService managerService;
    private ExpertService expertService;
    private CustomerService customerService;

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

    public boolean deleteExpertByUsername(String username) {
        Expert expert = expertService.findByEmail(username);
        return expertService.delete(expert);
    }

    public boolean editExpertByUsername(String username) {
        User expert;
        try {
            expert = expertService.findByEmail(username);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return false;
        }
        Expert editedExpert = getEditedExpert();
        return expertService.updateExpert(editedExpert);
    }

    private Expert getEditedExpert() {
        return null;
    }

    public boolean confirmExpert(Expert expert) {
        expert.setUserStatus(UserStatus.CONFIRMED);
        expertService.save(expert);
        return true;
    }

    public boolean confirmCustomer(Customer customer) {
        customer.setUserStatus(UserStatus.CONFIRMED);
        customerService.save(customer);
        return true;
    }
}
