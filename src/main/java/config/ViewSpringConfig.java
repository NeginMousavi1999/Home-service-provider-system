package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import view.UserView;

/**
 * @author Negin Mousavi
 */
@Configuration
public class ViewSpringConfig {
    @Bean
    public UserView userView() {
        return new UserView();
    }
}
