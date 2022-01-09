package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Negin Mousavi
 */
@ComponentScan(basePackages = {"dao", "service", "validation", "view"})
@Configuration
public class SpringConfig {
}
