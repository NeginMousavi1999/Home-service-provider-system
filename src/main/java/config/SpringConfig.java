package config;

import dao.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import validation.Validation;
import view.UserView;

/**
 * @author Negin Mousavi
 */
@Configuration
public class SpringConfig {
    @Bean
    public UserDao userDao() {
        return new UserDao();
    }

    @Bean
    public CommentDao commentDao() {
        return new CommentDao();
    }

    @Bean
    public OrderDao orderDao() {
        return new OrderDao();
    }

    @Bean
    public ServiceDao serviceDao() {
        return new ServiceDao();
    }

    @Bean
    public SubServiceDao subServiceDao() {
        return new SubServiceDao();
    }

    @Bean
    public SuggestionDao suggestionDao() {
        return new SuggestionDao();
    }

    @Bean
    public CustomerDao customerDao() {
        return new CustomerDao();
    }

    @Bean
    public ExpertDao expertDao() {
        return new ExpertDao();
    }

    @Bean
    public ManagerDao managerDao() {
        return new ManagerDao();
    }

    @Bean
    public Validation validation() {
        return new Validation();
    }

    @Bean
    public UserView userView() {
        return new UserView();
    }
}
