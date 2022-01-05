package config;

import dao.*;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import service.*;
import validation.Validation;

/**
 * @author Negin Mousavi
 */
@Import(value = {DaoSpringConfig.class, ValidationSpringConfig.class, MapperSpringConfig.class})
@Configuration
public class ServicesSpringConfig {

    @Bean
    public CustomerService customerService(CustomerDao customerDao) {
        CustomerService customerService = new CustomerService();
        customerService.setCustomerDao(customerDao);
        return customerService;
    }

    @Bean
    public ExpertService expertService(ExpertDao expertDao) {
        ExpertService expertService = new ExpertService();
        expertService.setExpertDao(expertDao);
        return expertService;
    }

    @Bean
    public ManagerService managerService(ManagerDao managerDao) {
        ManagerService managerService = new ManagerService();
        managerService.setManagerDao(managerDao);
        return managerService;
    }

    @Bean
    public UserService userService(Validation validation, CustomerService customerService, UserDao userDao, ModelMapper modelMapper) {
        UserService userService = new UserService();
        userService.setCustomerService(customerService);
        userService.setValidation(validation);
        userService.setUserDao(userDao);
        userService.setModelMapper(modelMapper);
        return userService;
    }

    @Bean
    public ServiceService serviceService(ServiceDao serviceDao, Validation validation) {
        ServiceService serviceService = new ServiceService();
        serviceService.setServiceDao(serviceDao);
        serviceService.setValidation(validation);
        return serviceService;
    }

    @Bean
    public SubServiceService subServiceService(SubServiceDao subServiceDao, Validation validation) {
        SubServiceService subServiceService = new SubServiceService();
        subServiceService.setSubServiceDao(subServiceDao);
        subServiceService.setValidation(validation);
        return subServiceService;
    }

    @Bean
    public OrderService orderService(OrderDao orderDao) {
        OrderService orderService = new OrderService();
        orderService.setOrderDao(orderDao);
        return orderService;
    }

    @Bean
    public SuggestionService suggestionService(SuggestionDao suggestionDao) {
        SuggestionService suggestionService = new SuggestionService();
        suggestionService.setSuggestionDao(suggestionDao);
        return suggestionService;
    }

    @Bean
    public CommentService commentService(CommentDao commentDao) {
        CommentService commentService = new CommentService();
        commentService.setCommentDao(commentDao);
        return commentService;
    }
}
