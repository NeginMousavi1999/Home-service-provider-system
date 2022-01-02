package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import service.*;
import validation.Validation;
import view.CustomerView;
import view.ExpertView;
import view.ManagerView;
import view.UserView;

/**
 * @author Negin Mousavi
 */
@Configuration
@Import(value = {ServicesSpringConfig.class, ValidationSpringConfig.class})
public class ViewSpringConfig {

    @Bean
    public ManagerView managerView(ServiceService serviceService, ManagerService managerService) {
        ManagerView managerView = new ManagerView();
        managerView.setServiceService(serviceService);
        managerView.setManagerService(managerService);
        return managerView;
    }

    @Bean
    public CustomerView customerView(CustomerService customerService, OrderService orderService, ExpertService expertService) {
        CustomerView customerView = new CustomerView();
        customerView.setCustomerService(customerService);
        customerView.setExpertService(expertService);
        customerView.setOrderService(orderService);
        return customerView;
    }

    @Bean
    public ExpertView expertView(ExpertService expertService) {
        ExpertView expertView = new ExpertView();
        expertView.setExpertService(expertService);
        return expertView;
    }

    @Bean
    public UserView userView(CustomerView customerView, ManagerView managerView, ExpertView expertView, Validation validation) {
        UserView userView = new UserView();
        userView.setCustomerView(customerView);
        userView.setExpertView(expertView);
        userView.setManagerView(managerView);
        userView.setValidation(validation);
        return userView;
    }
}
