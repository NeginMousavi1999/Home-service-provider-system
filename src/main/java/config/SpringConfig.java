package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import validation.Validation;
import view.UserView;

/**
 * @author Negin Mousavi
 */
@Configuration
public class SpringConfig {
/*    @Bean
    public UserDao userDao() {
        return new UserDao();
    }*/

    @Bean
    public Validation validation() {
        return new Validation();
    }

    @Bean
    public UserView userView() {
        return new UserView();
    }
}
