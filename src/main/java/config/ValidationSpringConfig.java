package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import validation.Validation;

/**
 * @author Negin Mousavi
 */
@Configuration
public class ValidationSpringConfig {
    @Bean
    public Validation validation() {
        return new Validation();
    }
}
