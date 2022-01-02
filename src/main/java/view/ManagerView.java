package view;

import lombok.Data;
import service.ServiceService;

/**
 * @author Negin Mousavi
 */
@Data
public class ManagerView {
    ServiceService serviceService;

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
