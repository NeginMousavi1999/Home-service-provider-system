package ir.maktab.view;

import ir.maktab.data.entity.members.Customer;
import ir.maktab.data.entity.members.Expert;
import ir.maktab.data.entity.members.User;
import ir.maktab.data.entity.services.Service;
import ir.maktab.data.entity.services.SubService;
import ir.maktab.data.enumuration.UserStatus;
import ir.maktab.service.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Negin Mousavi
 */
@RequiredArgsConstructor
@Component
@Getter
public class ManagerView {
    private final ServiceService serviceService;
    private final SubServiceService subServiceService;
    private final ManagerService managerService;
    private final ExpertService expertService;
    private final CustomerService customerService;

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
        /*try {
            expert = expertService.findByEmail(username);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return false;
        }*/
        return true/*expertService.delete(expert)*/;
    }

    public boolean editExpertScoreByUsername(String username, double score) {
        Expert expert;
        /*try {
            expert = expertService.findByEmail(username);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return false;
        }
        expert.setScore(score);*/
        return true/*expertService.update(expert)*/;
    }

    public boolean confirmExpert(Expert expert) {
        expert.setUserStatus(UserStatus.CONFIRMED);
//        expertService.update(expert);
        return true;
    }

    public boolean confirmCustomer(Customer customer) {
        customer.setUserStatus(UserStatus.CONFIRMED);
        customerService.update(customer);
        return true;
    }
}
