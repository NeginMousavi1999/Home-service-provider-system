package ir.maktab.view;

import ir.maktab.enumuration.UserStatus;
import lombok.Data;
import ir.maktab.model.members.Customer;
import ir.maktab.model.members.Expert;
import ir.maktab.model.members.User;
import ir.maktab.model.services.Service;
import ir.maktab.model.services.SubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ir.maktab.service.*;

/**
 * @author Negin Mousavi
 */
@Data
@Component
public class ManagerView {
    @Autowired
    private ServiceService serviceService;
    @Autowired
    private SubServiceService subServiceService;
    @Autowired
    private ManagerService managerService;
    @Autowired
    private ExpertService expertService;
    @Autowired
    private CustomerService customerService;

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
        Expert expert;
        try {
            expert = expertService.findByEmail(username);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return false;
        }
        return expertService.delete(expert);
    }

    public boolean editExpertScoreByUsername(String username, double score) {
        Expert expert;
        try {
            expert = expertService.findByEmail(username);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return false;
        }
        expert.setScore(score);
        return expertService.update(expert);
    }

    public boolean confirmExpert(Expert expert) {
        expert.setUserStatus(UserStatus.CONFIRMED);
        expertService.update(expert);
        return true;
    }

    public boolean confirmCustomer(Customer customer) {
        customer.setUserStatus(UserStatus.CONFIRMED);
        customerService.update(customer);
        return true;
    }
}
