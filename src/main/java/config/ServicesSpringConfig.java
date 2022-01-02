package config;

import dao.CustomerDao;
import dao.ServiceDao;
import dao.SubServiceDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import service.CustomerService;
import service.ServiceService;
import service.SubServiceService;
import service.UserService;
import validation.Validation;

/**
 * @author Negin Mousavi
 */
@Import(value = {DaoSpringConfig.class, ValidationSpringConfig.class})
@Configuration
public class ServicesSpringConfig {

    @Bean
    public CustomerService customerService(CustomerDao customerDao) {
        CustomerService customerService = new CustomerService();
        customerService.setCustomerDao(customerDao);
        return customerService;
    }

    @Bean
    public UserService userService(Validation validation, CustomerService customerService) {
        UserService userService = new UserService();
        userService.setCustomerService(customerService);
        userService.setValidation(validation);
        return userService;
    }

    @Bean
    public ServiceService serviceService(ServiceDao serviceDao) {
        ServiceService serviceService = new ServiceService();
        serviceService.setServiceDao(serviceDao);
        return serviceService;
    }

    @Bean
    public SubServiceService subServiceService(SubServiceDao subServiceDao) {
        SubServiceService subServiceService = new SubServiceService();
        subServiceService.setSubServiceDao(subServiceDao);
        return subServiceService;
    }
}
