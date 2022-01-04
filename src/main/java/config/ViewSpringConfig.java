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
    public ManagerView managerView(ServiceService serviceService, ManagerService managerService,
                                   SubServiceService subServiceService, ExpertService expertService
            , CustomerService customerService) {
        ManagerView managerView = new ManagerView();
        managerView.setServiceService(serviceService);
        managerView.setSubServiceService(subServiceService);
        managerView.setManagerService(managerService);
        managerView.setCustomerService(customerService);
        managerView.setExpertService(expertService);
        return managerView;
    }

    @Bean
    public CustomerView customerView(CustomerService customerService, OrderService orderService, ExpertService expertService,
                                     SubServiceService subServiceService) {
        CustomerView customerView = new CustomerView();
        customerView.setCustomerService(customerService);
        customerView.setExpertService(expertService);
        customerView.setOrderService(orderService);
        customerView.setSubServiceService(subServiceService);
        return customerView;
    }

    @Bean
    public ExpertView expertView(ExpertService expertService, ServiceService serviceService, SuggestionService suggestionService) {
        ExpertView expertView = new ExpertView();
        expertView.setExpertService(expertService);
        expertView.setServiceService(serviceService);
        expertView.setSuggestionService(suggestionService);
        return expertView;
    }

    @Bean
    public UserView userView(CustomerView customerView, ManagerView managerView, ExpertView expertView, Validation validation,
                             UserService userService) {
        UserView userView = new UserView();
        userView.setCustomerView(customerView);
        userView.setExpertView(expertView);
        userView.setManagerView(managerView);
        userView.setValidation(validation);
        userView.setUserService(userService);
        return userView;
    }
}
